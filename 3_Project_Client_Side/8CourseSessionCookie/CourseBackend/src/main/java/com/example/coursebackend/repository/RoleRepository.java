package com.example.coursebackend.repository;

import com.example.coursebackend.exception.NotFoundException;
import com.example.coursebackend.model.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        roles.add(new Role(1, "ADMIN"));
        roles.add(new Role(1, "USER"));
    }

    public Role getRoleByName(String name) {
        Optional<Role> role = roles.stream().filter(r -> r.getName().equals(name)).findFirst();
        if(role.isPresent()) {
            return role.get();
        }
        throw new NotFoundException("Không tìm thấy role có name = " + name);
    }

}
