package com.example.coursebackend.service;

import com.example.coursebackend.exception.BadRequestException;
import com.example.coursebackend.exception.NotFoundException;
import com.example.coursebackend.model.User;
import com.example.coursebackend.repository.SessionRepository;
import com.example.coursebackend.repository.UserRepository;
import com.example.coursebackend.request.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    public User login(LoginRequest request) {
        if(request.getEmail().equals("") || request.getPassword().equals("")) {
            throw new BadRequestException("Email hoặc password không được để trống");
        }

        Optional<User> userOptional = userRepository.findAll()
                .stream()
                .filter(user -> user.getEmail().equals(request.getEmail()) && user.getPassword().equals(request.getPassword()))
                .findFirst();

        if (userOptional.isEmpty()) {
            throw new NotFoundException("Email hoặc password không chính xác");
        }

        // Trả thông tin về phía client
        return userOptional.get();
    }
}
