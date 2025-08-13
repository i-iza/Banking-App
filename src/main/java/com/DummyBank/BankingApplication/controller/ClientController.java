package com.DummyBank.BankingApplication.controller;

import com.DummyBank.BankingApplication.entity.BankAccount;
import com.DummyBank.BankingApplication.entity.Card;
import com.DummyBank.BankingApplication.entity.Transaction;
import com.DummyBank.BankingApplication.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    // Request new bank account
    @PostMapping("/accounts")
    public ResponseEntity<BankAccount> requestAccount(@RequestBody BankAccount account) {
        return ResponseEntity.ok(clientService.requestBankAccount(account));
    }

    // Request debit card
    @PostMapping("/accounts/{accountId}/cards")
    public ResponseEntity<Card> requestDebitCard(@PathVariable Long accountId, @RequestParam BigDecimal monthlySalary) {
        return ResponseEntity.ok(clientService.requestDebitCard(accountId, monthlySalary));
    }

    // Perform transaction
    @PostMapping("/transactions")
    public ResponseEntity<Transaction> performTransaction(
            @RequestParam Long fromAccountId,
            @RequestParam Long toAccountId,
            @RequestParam BigDecimal amount
    ) {
        return ResponseEntity.ok(clientService.performTransaction(fromAccountId, toAccountId, amount));
    }

    // View own accounts
    @GetMapping("/{clientId}/accounts")
    public ResponseEntity<List<BankAccount>> getMyAccounts(@PathVariable Long clientId) {
        return ResponseEntity.ok(clientService.getMyAccounts(clientId));
    }

    // View own cards
    @GetMapping("/{clientId}/cards")
    public ResponseEntity<List<Card>> getMyCards(@PathVariable Long clientId) {
        return ResponseEntity.ok(clientService.getMyCards(clientId));
    }

    // View own transactions
    @GetMapping("/{clientId}/transactions")
    public ResponseEntity<List<Transaction>> getMyTransactions(@PathVariable Long clientId) {
        return ResponseEntity.ok(clientService.getMyTransactions(clientId));
    }
}

