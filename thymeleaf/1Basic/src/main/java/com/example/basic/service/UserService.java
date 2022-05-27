package com.example.basic.service;

import com.example.basic.model.Role;
import com.example.basic.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<User> users;

    public UserService() {
        users = new ArrayList<>();
        users.add(new User(1, "Nguyen Van A", "a@gmail.com", "0987654111", Role.ADMIN));
        users.add(new User(2, "Tran Van B", "b@gmail.com", "0987654222", Role.USER));
        users.add(new User(3, "Ngo Thi C", "b@gmail.com", "0987654333", Role.SALE));
        users.add(new User(4, "Bui Van D", "d@gmail.com", "0987654444", Role.ADMIN));
        users.add(new User(5, "Cao Thi E", "e@gmail.com", "0987654555", Role.USER));
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserById(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().get();
    }
}
