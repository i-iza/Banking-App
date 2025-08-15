package com.DummyBank.BankingApplication.dto;

import com.DummyBank.BankingApplication.entity.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {
    private Long id;
    private String iban;
    private Currency currency;
    private BigDecimal balance;
    private AccountStatus status;
    private UserDto client;

    public BankAccountDto(Long id, String iban, String currency, BigDecimal balance, AccountStatus status, UserDto dto) {
    }
}