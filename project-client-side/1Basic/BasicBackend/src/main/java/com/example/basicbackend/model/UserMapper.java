package com.example.basicbackend.model;

public class UserMapper {
    public static UserDto toUserDto(User u) {
        UserDto userDto = new UserDto();

        userDto.setId(u.getId());
        userDto.setUsername(u.getUsername());
        userDto.setEmail(u.getEmail());
        userDto.setAvatar(u.getAvatar());

        return userDto;
    }
}
