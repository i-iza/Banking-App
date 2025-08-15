package com.DummyBank.BankingApplication.mapper;

import com.DummyBank.BankingApplication.dto.BankAccountDto;
import com.DummyBank.BankingApplication.entity.BankAccount;

public class BankAccountMapper {
    public static BankAccountDto toDTO(BankAccount account) {
        if (account == null) return null;
        return new BankAccountDto(
                account.getId(),
                account.getIban(),
                account.getCurrency(),
                account.getBalance(),
                account.getStatus(),
                UserMapper.toDTO(account.getClient())
        );
    }
}
