package com.DummyBank.BankingApplication.controller;

import com.DummyBank.BankingApplication.entity.BankAccount;
import com.DummyBank.BankingApplication.entity.Card;
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

    // Approve bank account
    @PutMapping("/accounts/{id}/approve")
    public ResponseEntity<BankAccount> approveAccount(@PathVariable Long id) {
        return ResponseEntity.ok(bankerService.approveBankAccount(id));
    }

    // Reject bank account
    @PutMapping("/accounts/{id}/reject")
    public ResponseEntity<BankAccount> rejectAccount(@PathVariable Long id) {
        return ResponseEntity.ok(bankerService.rejectBankAccount(id));
    }

    // Approve card
    @PutMapping("/cards/{id}/approve")
    public ResponseEntity<Card> approveCard(@PathVariable Long id) {
        return ResponseEntity.ok(bankerService.approveCard(id));
    }

    // Reject card
    @PutMapping("/cards/{id}/reject")
    public ResponseEntity<Card> rejectCard(@PathVariable Long id, @RequestParam String reason) {
        return ResponseEntity.ok(bankerService.rejectCard(id, reason));
    }

    // View all accounts
    @GetMapping("/accounts")
    public ResponseEntity<List<BankAccount>> getAllAccounts() {
        return ResponseEntity.ok(bankerService.getAllAccounts());
    }

    // View all cards
    @GetMapping("/cards")
    public ResponseEntity<List<Card>> getAllCards() {
        return ResponseEntity.ok(bankerService.getAllCards());
    }
}

