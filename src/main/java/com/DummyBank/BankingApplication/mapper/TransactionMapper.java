package com.DummyBank.BankingApplication.mapper;


import com.DummyBank.BankingApplication.dto.TransactionDto;
import com.DummyBank.BankingApplication.entity.Transaction;

public class TransactionMapper {
    public static TransactionDto toDTO(Transaction tx) {
        if (tx == null) return null;
        return new TransactionDto(
                tx.getId(),
                BankAccountMapper.toDTO(tx.getAccount()),
                tx.getAmount(),
                tx.getCurrency(),
                tx.getType(),
                tx.getCreatedAt()
        );
    }
}
