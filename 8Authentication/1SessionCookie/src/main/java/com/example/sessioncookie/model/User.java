package com.example.sessioncookie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private Role[] roles;

    public boolean hasRole(String roleName) {
        return Arrays.stream(getRoles()).anyMatch(role -> role.getName().equals(roleName));
    }

    public boolean canPassRoute(String route) {
        return Arrays.stream(getRoles()).anyMatch(role -> Arrays.asList(role.getRoutes()).contains(route));
    }
}
