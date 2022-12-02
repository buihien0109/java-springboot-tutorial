package com.example.basic.service;

import com.example.basic.entity.User;
import com.example.basic.exception.BadRequestException;
import com.example.basic.repository.UserRepository;
import com.example.basic.request.LoginRequest;
import com.example.basic.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.example.basic.constant.Constant.MAX_AGE_COOKIE;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    // LOGIN USER
    public String login(LoginRequest request, HttpServletResponse httpServletResponse) {
        try {
            // Tạo đối tượng xác thực dựa trên thông tin request
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

            // Tiến hành xác thực
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // Lưu trữ thông tin của đối tượng đã đăng nhập
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            User user = (User) authentication.getPrincipal();
            String jwtToken = jwtUtils.generateToken(user);

            // Lưu thông tin vào trong cookie
            Cookie cookie = new Cookie("JWT_TOKEN", jwtToken);
            cookie.setMaxAge(MAX_AGE_COOKIE);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            httpServletResponse.addCookie(cookie);

            return jwtToken;
        } catch (Exception ex) {
            throw new BadRequestException("Email hoặc mật khẩu không chính xác");
        }
    }

    // LOGOUT USER
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout success";
    }
}
