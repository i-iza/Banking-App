package com.DummyBank.BankingApplication.service;

import com.DummyBank.BankingApplication.entity.Role;
import com.DummyBank.BankingApplication.entity.User;
import com.DummyBank.BankingApplication.repository.RoleRepository;
import com.DummyBank.BankingApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User createBanker(User banker) {
        Role bankerRole = roleRepository.findByName("BANKER")
                .orElseThrow(() -> new RuntimeException("Role BANKER not found"));
        banker.setRole(bankerRole);
        banker.setPassword(passwordEncoder.encode(banker.getPassword()));
        return userRepository.save(banker);
    }

    public User updateBanker(Long bankerId, User updatedBanker) {
        User existing = userRepository.findById(bankerId)
                .orElseThrow(() -> new RuntimeException("Banker not found"));
        existing.setFullName(updatedBanker.getFullName());
        existing.setEmail(updatedBanker.getEmail());
        return userRepository.save(existing);
    }

    public void deleteBanker(Long bankerId) {
        userRepository.deleteById(bankerId);
    }

    public List<User> getAllBankers() {
        Role bankerRole = roleRepository.findByName("BANKER")
                .orElseThrow(() -> new RuntimeException("Role BANKER not found"));
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().equals(bankerRole))
                .toList();
    }
}