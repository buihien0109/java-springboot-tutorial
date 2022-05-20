package com.example.blogbackend.model.mapper;

import com.example.blogbackend.model.User;
import com.example.blogbackend.model.dto.UserDto;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAvatar(user.getAvatar());
        userDto.setStatus(user.isStatus());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}