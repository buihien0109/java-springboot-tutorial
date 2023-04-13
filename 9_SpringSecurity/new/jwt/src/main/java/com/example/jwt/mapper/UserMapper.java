package com.example.jwt.mapper;

import com.example.jwt.dto.UserDto;
import com.example.jwt.entity.User;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles()
        );
        return userDto;
    }
}
