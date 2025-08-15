package com.DummyBank.BankingApplication.controller;

import com.DummyBank.BankingApplication.dto.UserDto;
import com.DummyBank.BankingApplication.entity.User;
import com.DummyBank.BankingApplication.mapper.UserMapper;
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

    @PostMapping("/bankers")
    public ResponseEntity<UserDto> createBanker(@RequestBody User banker) {
        return ResponseEntity.ok(UserMapper.toDTO(adminService.createBanker(banker)));
    }

    @PutMapping("/bankers/{id}")
    public ResponseEntity<UserDto> updateBanker(@PathVariable Long id, @RequestBody User updated) {
        return ResponseEntity.ok(UserMapper.toDTO(adminService.updateBanker(id, updated)));
    }

    @DeleteMapping("/bankers/{id}")
    public ResponseEntity<Void> deleteBanker(@PathVariable Long id) {
        adminService.deleteBanker(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/bankers")
    public ResponseEntity<List<UserDto>> getAllBankers() {
        return ResponseEntity.ok(adminService.getAllBankers()
                .stream()
                .map(UserMapper::toDTO)
                .toList());
    }
}
