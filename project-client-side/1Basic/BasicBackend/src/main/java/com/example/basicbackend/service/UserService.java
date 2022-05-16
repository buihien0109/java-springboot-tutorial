package com.example.basicbackend.service;

import com.example.basicbackend.exception.NotFoundException;
import com.example.basicbackend.model.User;
import com.example.basicbackend.model.UserDto;
import com.example.basicbackend.model.UserMapper;
import com.example.basicbackend.request.LoginRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users;

    public UserService() {
        users = new ArrayList<>();
        users.add(new User(1, "bob", "bob@gmail.com", "123", "https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8cGVvcGxlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=800&q=60"));
        users.add(new User(2, "anna", "anna@gmail.com", "456", "https://images.unsplash.com/photo-1534528741775-53994a69daeb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8cGVvcGxlfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=800&q=60"));
        users.add(new User(3, "alice", "alice@gmail.com", "789", "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTN8fHBlb3BsZXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=800&q=60"));
    }

    public UserDto login(LoginRequest request) {
        Optional<User> userOptional = users
                .stream()
                .filter(user -> user.getUsername().equals(request.getUsername()) && user.getPassword().equals(request.getPassword()))
                .findFirst();

        if (userOptional.isPresent()) {
            return UserMapper.toUserDto(userOptional.get());
        }

        throw new NotFoundException("Tài khoản hoặc mật khẩu không chính xác");
    }
}
