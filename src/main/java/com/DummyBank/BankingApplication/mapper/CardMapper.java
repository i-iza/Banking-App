package com.DummyBank.BankingApplication.mapper;

import com.DummyBank.BankingApplication.dto.CardDto;
import com.DummyBank.BankingApplication.entity.Card;

public class CardMapper {
    public static CardDto toDTO(Card card) {
        if (card == null) return null;
        return new CardDto(
                card.getId(),
                card.getType(),
                card.getStatus(),
                card.getMonthlySalary(),
                card.getRejectionReason(),
                BankAccountMapper.toDTO(card.getAccount())
        );
    }
}

