package com.DummyBank.BankingApplication.controller;

import com.DummyBank.BankingApplication.dto.BankAccountDto;
import com.DummyBank.BankingApplication.dto.CardDto;
import com.DummyBank.BankingApplication.dto.TransactionDto;
import com.DummyBank.BankingApplication.entity.BankAccount;
import com.DummyBank.BankingApplication.entity.Card;
import com.DummyBank.BankingApplication.entity.Transaction;
import com.DummyBank.BankingApplication.mapper.BankAccountMapper;
import com.DummyBank.BankingApplication.mapper.CardMapper;
import com.DummyBank.BankingApplication.mapper.TransactionMapper;
import com.DummyBank.BankingApplication.service.ClientService;
import lombok.Data;
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

    // --- Create new bank account ---
    @PostMapping("/accounts")
    public ResponseEntity<BankAccountDto> requestBankAccount(@RequestBody BankAccountRequest request) {
        BankAccount account = new BankAccount();
        account.setIban(request.getIban());
        account.setCurrency(String.valueOf(request.getCurrency()));
        account.setClient(request.getClient());
        return ResponseEntity.ok(
                BankAccountMapper.toDTO(clientService.requestBankAccount(account))
        );
    }

    // --- Request debit card ---
    @PostMapping("/accounts/{accountId}/cards")
    public ResponseEntity<CardDto> requestDebitCard(
            @PathVariable Long accountId,
            @RequestParam BigDecimal monthlySalary
    ) {
        Card card = clientService.requestDebitCard(accountId, monthlySalary);
        return ResponseEntity.ok(CardMapper.toDTO(card));
    }

    // --- Perform transaction ---
    @PostMapping("/transactions")
    public ResponseEntity<TransactionDto> performTransaction(@RequestBody TransactionRequest request) {
        Transaction tx = clientService.performTransaction(
                request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmount()
        );
        return ResponseEntity.ok(TransactionMapper.toDTO(tx));
    }

    // --- Get my accounts ---
    @GetMapping("/{clientId}/accounts")
    public ResponseEntity<List<BankAccountDto>> getMyAccounts(@PathVariable Long clientId) {
        return ResponseEntity.ok(
                clientService.getMyAccounts(clientId).stream()
                        .map(BankAccountMapper::toDTO)
                        .toList()
        );
    }

    // --- Get my cards ---
    @GetMapping("/{clientId}/cards")
    public ResponseEntity<List<CardDto>> getMyCards(@PathVariable Long clientId) {
        return ResponseEntity.ok(
                clientService.getMyCards(clientId).stream()
                        .map(CardMapper::toDTO)
                        .toList()
        );
    }

    // --- Get my transactions ---
    @GetMapping("/{clientId}/transactions")
    public ResponseEntity<List<TransactionDto>> getMyTransactions(@PathVariable Long clientId) {
        return ResponseEntity.ok(
                clientService.getMyTransactions(clientId).stream()
                        .map(TransactionMapper::toDTO)
                        .toList()
        );
    }

    @Data
    public static class BankAccountRequest {
        private String iban;
        private String currency;
        private com.DummyBank.BankingApplication.entity.User client;
    }

    @Data
    public static class TransactionRequest {
        private Long fromAccountId;
        private Long toAccountId;
        private BigDecimal amount;
    }
}
