package com.example.coursebackend.service;

import com.example.coursebackend.exception.NotFoundException;
import com.example.coursebackend.model.User;
import com.example.coursebackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        Optional<User> userOptional =  userRepository.getUserById(id);

        if(userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new NotFoundException("Không tìm thấy user có id = " + id);
    }
}
