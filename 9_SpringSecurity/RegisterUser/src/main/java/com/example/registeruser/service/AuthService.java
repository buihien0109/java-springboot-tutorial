package com.example.registeruser.service;

import com.example.registeruser.entity.Token;
import com.example.registeruser.entity.User;
import com.example.registeruser.request.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Đăng ký user
    public String register(RegisterUserRequest request) {
        // Lấy thông tin user dựa trên email
        boolean userExists = userService
                .getUser(request.getEmail())
                .isPresent();

        if (userExists) {
            // Nếu user được tìm thấy có trùng các thuộc tính và chưa được kích hoạt
            // Gửi email kích hoạt

            throw new RuntimeException("Email = " + request.getEmail() + " đã tồn tại");
        }

        // Mã hóa password
        String passwordEncode = passwordEncoder.encode(request.getPassword());

        // Tạo user và lưu vào database
        User user = new User(request.getName(), request.getEmail(), passwordEncode, new ArrayList<>(List.of("USER")));
        userService.saveUser(user);

        // Sinh ra token
        String tokenString = UUID.randomUUID().toString();

        // Tạo token và lưu token
        Token token = new Token(
                tokenString,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        tokenService.saveToken(token);

        String link = "http://localhost:8080/api/auth/confirm?token=" + tokenString;

        return link;
    }

    // Verify Token
    public String confirmToken(String tokenString) {
        // Lấy thông tin của token
        Token token = tokenService.getToken(tokenString).orElseThrow(() ->
                new RuntimeException("token not found")
        );

        // Xem token đã được confirm hay chưa
        if (token.getConfirmedAt() != null) {
            throw new RuntimeException("email already confirmed");
        }

        // Xem token đã hết hạn chưa
        LocalDateTime expiredAt = token.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        // Active token
        tokenService.setConfirmedAt(tokenString);

        // Active user
        userService.enableUser(token.getUser().getEmail());

        return "confirmed";
    }
}
