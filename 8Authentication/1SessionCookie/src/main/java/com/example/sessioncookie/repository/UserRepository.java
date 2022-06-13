package com.example.sessioncookie.repository;

import com.example.sessioncookie.model.Role;
import com.example.sessioncookie.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users;

    private final RoleRepository roleRepository;

    public UserRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        init();
    }

    public void init() {
        users = new ArrayList<>();

        Role roleAdmin = roleRepository.getRoleByName("ADMIN");
        Role roleUser = roleRepository.getRoleByName("USER");

        users.add(new User(1, "Bui Hien", "hien@gmail.com", "111", new Role[]{roleAdmin, roleUser}));
        users.add(new User(2, "Thu Hang", "hang@gmail.com", "111", new Role[]{roleUser}));
    }

    public List<User> findAll() {
        return users;
    }
}
