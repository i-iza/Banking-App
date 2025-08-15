package com.DummyBank.BankingApplication.dto;

import com.DummyBank.BankingApplication.entity.enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    private Long id;
    private String type;
    private CardStatus status;
    private BigDecimal monthlySalary;
    private String rejectionReason;
    private BankAccountDto account;
}
