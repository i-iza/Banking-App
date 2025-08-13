package com.DummyBank.BankingApplication.entity;

import com.DummyBank.BankingApplication.entity.enums.CardStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 16)
    private String cardNumber;

    @Column(nullable = false, length = 20)
    private String type; // DEBIT_CARD

    @Column(precision = 15, scale = 2)
    private BigDecimal monthlySalary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private CardStatus status; // PENDING, APPROVED, REJECTED

    private String rejectionReason;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @ToString.Exclude
    private BankAccount account;
}
