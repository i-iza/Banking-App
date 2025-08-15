package com.DummyBank.BankingApplication.dto;

import com.DummyBank.BankingApplication.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Long id;
    private BankAccountDto account;
    private BigDecimal amount;
    private Currency currency;
    private TransactionType type;
    private Instant createdAt;

    public TransactionDto(Long id, BankAccountDto dto, BigDecimal amount, String currency, TransactionType type, LocalDateTime createdAt) {
    }
}