package com.example.basic.service;

import com.example.basic.entity.User;
import com.example.basic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> {
            throw new UsernameNotFoundException("Not found user with email = " + email);
        });
    }

    // Lưu user
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // Lấy thông tin của user dựa trên email
    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }

    // Active user
    public void enableUser(String email) {
        userRepository.enableUser(email);
    }
}
