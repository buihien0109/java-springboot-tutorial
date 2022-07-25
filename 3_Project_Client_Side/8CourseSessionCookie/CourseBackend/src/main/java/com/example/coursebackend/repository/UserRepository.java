package com.example.coursebackend.repository;

import com.example.coursebackend.model.Role;
import com.example.coursebackend.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        users.add(new User(1, "Phạm Thị Mẫn", "man@gmail.com", "0988888888", null, new Role[]{roleRepository.getRoleByName("ADMIN"), roleRepository.getRoleByName("USER")}, "111"));
        users.add(new User(2, "Nguyễn Đức Thịnh", "thinh@gmail.com", "0977777777", null, new Role[]{roleRepository.getRoleByName("USER")}, "111"));
        users.add(new User(3, "Nguyễn Thanh Hương", "huong@gmail.com", "0966666666", null, new Role[]{roleRepository.getRoleByName("USER")}, "111"));
    }

    public List<User> findAll() {
        return users;
    }

    public Optional<User> getUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }
}
