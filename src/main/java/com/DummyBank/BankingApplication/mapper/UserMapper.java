package com.DummyBank.BankingApplication.mapper;

import com.DummyBank.BankingApplication.dto.UserDto;
import com.DummyBank.BankingApplication.entity.User;

public class UserMapper {
    public static UserDto toDTO(User user) {
        if (user == null) return null;
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                RoleMapper.toDTO(user.getRole())
        );
    }
}
