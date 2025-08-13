package com.DummyBank.BankingApplication.controller;

import com.DummyBank.BankingApplication.entity.User;
import com.DummyBank.BankingApplication.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // Create banker
    @PostMapping("/bankers")
    public ResponseEntity<User> createBanker(@RequestBody User banker) {
        return ResponseEntity.ok(adminService.createBanker(banker));
    }

    // Update banker
    @PutMapping("/bankers/{id}")
    public ResponseEntity<User> updateBanker(@PathVariable Long id, @RequestBody User banker) {
        return ResponseEntity.ok(adminService.updateBanker(id, banker));
    }

    // Delete banker
    @DeleteMapping("/bankers/{id}")
    public ResponseEntity<Void> deleteBanker(@PathVariable Long id) {
        adminService.deleteBanker(id);
        return ResponseEntity.noContent().build();
    }

    // View all bankers
    @GetMapping("/bankers")
    public ResponseEntity<List<User>> getAllBankers() {
        return ResponseEntity.ok(adminService.getAllBankers());
    }
}
