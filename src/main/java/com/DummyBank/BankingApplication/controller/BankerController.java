package com.DummyBank.BankingApplication.controller;


import com.DummyBank.BankingApplication.dto.BankAccountDto;
import com.DummyBank.BankingApplication.dto.CardDto;
import com.DummyBank.BankingApplication.dto.TransactionDto;
import com.DummyBank.BankingApplication.dto.UserDto;
import com.DummyBank.BankingApplication.entity.BankAccount;
import com.DummyBank.BankingApplication.entity.Card;
import com.DummyBank.BankingApplication.entity.User;
import com.DummyBank.BankingApplication.entity.enums.AccountStatus;
import com.DummyBank.BankingApplication.entity.enums.CardStatus;
import com.DummyBank.BankingApplication.mapper.BankAccountMapper;
import com.DummyBank.BankingApplication.mapper.CardMapper;
import com.DummyBank.BankingApplication.mapper.TransactionMapper;
import com.DummyBank.BankingApplication.mapper.UserMapper;
import com.DummyBank.BankingApplication.repository.BankAccountRepository;
import com.DummyBank.BankingApplication.service.BankerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banker")
@RequiredArgsConstructor
public class BankerController {

    private final BankerService bankerService;

    @GetMapping("/accounts")
    public ResponseEntity<List<BankAccountDto>> getAllAccounts() {
        return ResponseEntity.ok(bankerService.getAllAccounts()
                .stream()
                .map(BankAccountMapper::toDTO)
                .toList());
    }

    @GetMapping("/cards")
    public ResponseEntity<List<CardDto>> getAllCards() {
        return ResponseEntity.ok(bankerService.getAllCards()
                .stream()
                .map(CardMapper::toDTO)
                .toList());
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        return ResponseEntity.ok(bankerService.getAllTransactions()
                .stream()
                .map(TransactionMapper::toDTO)
                .toList());
    }

    @PostMapping("/clients")
    public ResponseEntity<UserDto> createClient(@RequestBody User client) {
        return ResponseEntity.ok(UserMapper.toDTO(bankerService.createClient(client)));
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<UserDto> updateClient(@PathVariable Long id, @RequestBody User client) {
        return ResponseEntity.ok(UserMapper.toDTO(bankerService.updateClient(id, client)));
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        bankerService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    // Approve bank account
    @PutMapping("/accounts/{accountId}/approve")
    public ResponseEntity<BankAccountDto> approveBankAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(
                BankAccountMapper.toDTO(bankerService.approveBankAccount(accountId))
        );
    }

    // Reject bank account
    @PutMapping("/accounts/{accountId}/reject")
    public ResponseEntity<BankAccountDto> rejectBankAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(
                BankAccountMapper.toDTO(bankerService.rejectBankAccount(accountId))
        );
    }

    // Approve card
    @PutMapping("/cards/{cardId}/approve")
    public ResponseEntity<CardDto> approveCard(@PathVariable Long cardId) {
        return ResponseEntity.ok(
                CardMapper.toDTO(bankerService.approveCard(cardId))
        );
    }

    // Reject card
    @PutMapping("/cards/{cardId}/reject")
    public ResponseEntity<CardDto> rejectCard(
            @PathVariable Long cardId,
            @RequestParam(required = false) String reason
    ) {
        return ResponseEntity.ok(
                CardMapper.toDTO(bankerService.rejectCard(cardId, reason))
        );
    }

}
