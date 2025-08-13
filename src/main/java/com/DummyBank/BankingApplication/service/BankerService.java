package com.DummyBank.BankingApplication.service;

import com.DummyBank.BankingApplication.entity.BankAccount;
import com.DummyBank.BankingApplication.entity.Card;
import com.DummyBank.BankingApplication.entity.enums.AccountStatus;
import com.DummyBank.BankingApplication.entity.enums.CardStatus;
import com.DummyBank.BankingApplication.repository.BankAccountRepository;
import com.DummyBank.BankingApplication.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankerService {

    private final BankAccountRepository bankAccountRepository;
    private final CardRepository cardRepository;

    public BankAccount approveBankAccount(Long accountId) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setStatus(AccountStatus.ACTIVE);
        return bankAccountRepository.save(account);
    }

    public BankAccount rejectBankAccount(Long accountId) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setStatus(AccountStatus.SUSPENDED);
        return bankAccountRepository.save(account);
    }

    public Card approveCard(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        card.setStatus(CardStatus.APPROVED);
        return cardRepository.save(card);
    }

    public Card rejectCard(Long cardId, String reason) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        card.setStatus(CardStatus.REJECTED);
        card.setRejectionReason(reason);
        return cardRepository.save(card);
    }

    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }
}
