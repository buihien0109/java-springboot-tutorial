package com.example.basic.service;

import com.example.basic.entity.Token;
import com.example.basic.entity.User;
import com.example.basic.exception.BadRequestException;
import com.example.basic.exception.NotFoundException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // LOGIN USER
    public String login(LoginRequest request, HttpSession session) {
        // Tạo đối tượng xác thực dựa trên thông tin request
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        // Tiến hành xác thực
        Authentication authentication = authenticationManager.authenticate(token);

        // Lưu trữ thông tin của đối tượng đã đăng nhập
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Lưu thông tin vào trong session
        session.setAttribute("MY_SESSION", authentication.getName());

        return "login success";
    }

    // LOGOUT USER
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout success";
    }

    // REGISTER USER
    public String register(RegisterUserRequest request) {
        // Lấy thông tin user dựa trên email
        Optional<User> userOptional = userService.getUser(request.getEmail());

        if (userOptional.isPresent()) {
            // Nếu user được tìm thấy có trùng các thuộc tính và chưa được kích hoạt
            // Gửi email kích hoạt
            User user = userOptional.get();
            if (!user.getEnabled()
                    && user.getName().equals(request.getName())
                    && user.getEmail().equals(request.getEmail())) {
                return generateTokenAndSendMail(user);
            }

            throw new BadRequestException("Email = " + request.getEmail() + " đã tồn tại");
        }

        // Mã hóa password
        String passwordEncode = passwordEncoder.encode(request.getPassword());

        // Tạo user và lưu vào database
        User newUser = new User(request.getName(), request.getEmail(), passwordEncode, new ArrayList<>(List.of("USER")));
        userService.saveUser(newUser);

        // Sinh ra token
        return generateTokenAndSendMail(newUser);
    }

    // SINH TOKEN - SEND MAIL
    private String generateTokenAndSendMail(User user) {
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

        // Gửi email
        String link = "http://localhost:8080/api/auth/confirm?token=" + tokenString;
        mailService.send(user.getEmail(), "Xác thực tài khoản", link);

        return link;
    }

    // VERIFY TOKEN
    public String confirmToken(String tokenString) {
        // Lấy thông tin của token
        Token token = tokenService.getToken(tokenString).orElseThrow(() ->
                new NotFoundException("Không tìm thấy token")
        );

        // Xem token đã được confirm hay chưa
        if (token.getConfirmedAt() != null) {
            throw new BadRequestException("Token đã được xác thực");
        }

        // Xem token đã hết hạn chưa
        LocalDateTime expiredAt = token.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Token đã hết thời gian");
        }

        // Active token
        tokenService.setConfirmedAt(tokenString);

        // Active user
        userService.enableUser(token.getUser().getEmail());

        return "confirmed";
    }
}
