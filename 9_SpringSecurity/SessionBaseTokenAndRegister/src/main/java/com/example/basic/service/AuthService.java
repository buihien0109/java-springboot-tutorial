package com.example.basic.service;

import com.example.basic.entity.Token;
import com.example.basic.entity.User;
import com.example.basic.exception.BadRequestException;
import com.example.basic.repository.TokenRepository;
import com.example.basic.repository.UserRepository;
import com.example.basic.request.LoginRequest;
import com.example.basic.request.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MailService mailService;

    @Autowired
    private TokenService tokenService;

    // == ĐĂNG NHẬP ==
    public void login(LoginRequest request, HttpSession session) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            session.setAttribute("TECHMASTER_SESSION", authentication.getName());
        } catch (DisabledException e) {
            throw new BadRequestException("Tài khoản chưa được kích hoạt");
        } catch (AuthenticationException e) {
            throw new BadRequestException("Email hoặc mật khẩu không chính xác");
        }
    }

    // == ĐĂNG KÝ ==
    public String register(RegisterUserRequest request) {
        // Kiểm tra xem email đã tồn tại hay chưa
        boolean userExists = userRepository.existsByEmail(request.getEmail());
        if(userExists) {
            throw new BadRequestException("Email đã tồn tại");
        }

        // Mã hóa password của user
        String encodedPassword = bCryptPasswordEncoder
                .encode(request.getPassword());

        // Tạo ra đối tượng user
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encodedPassword)
                .role(List.of("USER"))
                .enabled(false)
                .build();

        // Lưu thông tin của user
        userRepository.save(user);

        // Generate token
        String token = UUID.randomUUID().toString();

        // Tạo đối tượng token
        Token confirmationToken = new Token(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        tokenRepository.save(confirmationToken);


        String link = "http://localhost:8080/auth/confirm?token=" + token;
        mailService.send(request.getEmail(), "Confirm Account", link);

        return token;
    }

    // == XÁC THỰC TÀI KHOẢN ==
    public String confirm(String token) {
        // Lấy ra thông tin của token
        Token confirmationToken = tokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("Không tìm thấy token"));

        // Kiểm tra xem token đã được kích hoạt hay chưa
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Tài khoản đã được kích hoạt");
        }

        // Lấy ra thời gian hết hạn token
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        // Kiểm tra token đã hết hạn hay chưa
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token đã hết hạn");
        }

        // Set thời gian kích hoạt của token
        tokenService.setConfirmedAt(token);

        // Kích hoạt user
        enableUser(confirmationToken.getUser().getEmail());

        return "confirmed";
    }

    public void enableUser(String email) {
        userRepository.enableUser(email);
    }
}
