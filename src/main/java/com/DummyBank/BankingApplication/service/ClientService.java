package com.DummyBank.BankingApplication.service;

import com.DummyBank.BankingApplication.entity.BankAccount;
import com.DummyBank.BankingApplication.entity.Card;
import com.DummyBank.BankingApplication.entity.Transaction;
import com.DummyBank.BankingApplication.entity.User;
import com.DummyBank.BankingApplication.entity.enums.AccountStatus;
import com.DummyBank.BankingApplication.entity.enums.CardStatus;
import com.DummyBank.BankingApplication.entity.enums.TransactionType;
import com.DummyBank.BankingApplication.repository.BankAccountRepository;
import com.DummyBank.BankingApplication.repository.CardRepository;
import com.DummyBank.BankingApplication.repository.TransactionRepository;
import com.DummyBank.BankingApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final BankAccountRepository bankAccountRepository;
    private final CardRepository cardRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public BankAccount requestBankAccount(BankAccount account) {
        account.setStatus(AccountStatus.SUSPENDED); // pending banker approval
        account.setBalance(BigDecimal.ZERO);
        return bankAccountRepository.save(account);
    }

    public Card requestDebitCard(Long accountId, BigDecimal monthlySalary) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (!account.getStatus().equals(AccountStatus.ACTIVE)) {
            throw new RuntimeException("Account must be active to request a card");
        }

        Card card = Card.builder()
                .account(account)
                .type("DEBIT_CARD")
                .monthlySalary(monthlySalary)
                .status(monthlySalary.compareTo(BigDecimal.valueOf(500)) < 0
                        ? CardStatus.REJECTED
                        : CardStatus.PENDING)
                .build();

        return cardRepository.save(card);
    }

    public Transaction performTransaction(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        BankAccount from = bankAccountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Source account not found"));
        BankAccount to = bankAccountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Destination account not found"));

        if (from.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        // Debit from source
        from.setBalance(from.getBalance().subtract(amount));
        bankAccountRepository.save(from);
        Transaction debit = transactionRepository.save(Transaction.builder()
                .account(from)
                .amount(amount)
                .currency(from.getCurrency())
                .type(TransactionType.DEBIT)
                .build());

        // Credit to destination
        to.setBalance(to.getBalance().add(amount));
        bankAccountRepository.save(to);
        transactionRepository.save(Transaction.builder()
                .account(to)
                .amount(amount)
                .currency(to.getCurrency())
                .type(TransactionType.CREDIT)
                .build());

        return debit;
    }

    public List<BankAccount> getMyAccounts(Long clientId) {
        User client = userRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return bankAccountRepository.findByClient(client);
    }

    public List<Card> getMyCards(Long clientId) {
        return getMyAccounts(clientId).stream()
                .map(BankAccount::getCard)
                .toList();
    }

    public List<Transaction> getMyTransactions(Long clientId) {
        return getMyAccounts(clientId).stream()
                .flatMap(account -> transactionRepository.findByAccount(account).stream())
                .toList();
    }
}