package com.DummyBank.BankingApplication.repository;

import com.DummyBank.BankingApplication.entity.BankAccount;
import com.DummyBank.BankingApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findByIban(String iban);
    List<BankAccount> findByClient(User client);
}