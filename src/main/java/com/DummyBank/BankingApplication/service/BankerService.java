package com.DummyBank.BankingApplication.service;

import com.DummyBank.BankingApplication.entity.*;
import com.DummyBank.BankingApplication.entity.enums.AccountStatus;
import com.DummyBank.BankingApplication.entity.enums.CardStatus;
import com.DummyBank.BankingApplication.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankerService {

    private final BankAccountRepository bankAccountRepository;
    private final CardRepository cardRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

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

    public User createClient(User client) {
        Role clientRole = roleRepository.findByName("CLIENT")
                .orElseThrow(() -> new RuntimeException("Role CLIENT not found"));
        client.setRole(clientRole);
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return userRepository.save(client);
    }

    public User updateClient(Long clientId, User updatedClient) {
        User existing = userRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        existing.setFullName(updatedClient.getFullName());
        existing.setEmail(updatedClient.getEmail());
        existing.setUsername(updatedClient.getUsername());
        return userRepository.save(existing);
    }

    public void deleteClient(Long clientId) {
        userRepository.deleteById(clientId);
    }

    public List<User> getAllClients() {
        Role clientRole = roleRepository.findByName("CLIENT")
                .orElseThrow(() -> new RuntimeException("Role CLIENT not found"));
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().equals(clientRole))
                .toList();
    }

    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
