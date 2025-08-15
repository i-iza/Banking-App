package com.DummyBank.BankingApplication.mapper;


import com.DummyBank.BankingApplication.dto.RoleDto;
import com.DummyBank.BankingApplication.entity.Role;

public class RoleMapper {
    public static RoleDto toDTO(Role role) {
        if (role == null) return null;
        return new RoleDto(role.getId(), role.getName());
    }
}

