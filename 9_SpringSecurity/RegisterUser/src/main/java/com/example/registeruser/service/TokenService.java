package com.example.registeruser.service;

import com.example.registeruser.entity.Token;
import com.example.registeruser.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    // Lưu token
    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

    // Lấy thông tin của token
    public Optional<Token> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    // Confirm time token
    public void setConfirmedAt(String token) {
        tokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
