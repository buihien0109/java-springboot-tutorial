package com.example.blogbackend.model.dto;

import com.example.blogbackend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String avatar;
    private boolean status;
    private List<Role> roles;
}

