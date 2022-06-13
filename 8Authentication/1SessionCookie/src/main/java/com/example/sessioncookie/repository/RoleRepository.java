package com.example.sessioncookie.repository;

import com.example.sessioncookie.exception.NotFoundException;
import com.example.sessioncookie.model.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepository {
    private List<Role> roles;

    public RoleRepository() {
        init();
    }

    public void init() {
        roles = new ArrayList<>();

        Role roleUser = Role.builder()
                .id(1)
                .name("USER")
                .routes(new String[]{
                        "/user",
                        "/api/v1/message"
                })
                .build();

        Role roleAdmin = Role.builder()
                .id(2)
                .name("ADMIN")
                .routes(new String[]{
                        "/user",
                        "/admin",
                        "/api/v1/message",
                        "/api/v1/admin/message"
                })
                .build();

        roles.add(roleUser);
        roles.add(roleAdmin);
    }

    public List<String> getAllRoutes() {
        List<String> routes = new ArrayList<>();
        roles.forEach(role -> routes.addAll(Arrays.asList(role.getRoutes())));
        return routes;
    }

    public Role getRoleByName(String roleName) {
        Optional<Role> roleOptional = roles.stream().filter(role -> role.getName().equals(roleName)).findFirst();
        if (roleOptional.isEmpty()) {
            throw new NotFoundException("Không tìm thấy role có tên = " + roleName);
        }
        return roleOptional.get();
    }
}
