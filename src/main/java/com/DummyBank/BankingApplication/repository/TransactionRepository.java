package com.DummyBank.BankingApplication.repository;

import com.DummyBank.BankingApplication.entity.BankAccount;
import com.DummyBank.BankingApplication.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount(BankAccount account);
}
