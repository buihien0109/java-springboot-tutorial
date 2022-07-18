package com.example.sessioncookie.service;

import com.example.sessioncookie.model.User;
import com.example.sessioncookie.repository.SessionRepository;
import com.example.sessioncookie.repository.UserRepository;
import com.example.sessioncookie.request.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WebService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    public User login(LoginRequest request, HttpServletResponse response) {
        if (request.getEmail().equals("") || request.getPassword().equals("")) {
            throw new RuntimeException("Email hoặc password không được để trống");
        }

        Optional<User> userOptional = userRepository.findAll()
                .stream()
                .filter(user -> user.getEmail().equals(request.getEmail()) && user.getPassword().equals(request.getPassword()))
                .findFirst();

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Email hoặc password không chính xác");
        }

        // Tạo token
        String generateToken = UUID.randomUUID().toString();

        // Set cookie
        Cookie cookie = new Cookie("websession", generateToken);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);

        // Lưu thông tin vào session
        sessionRepository.save(generateToken, userOptional.get());

        // Trả thông tin về phía client
        return userOptional.get();
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = WebUtils.getCookie(request, "websession");
        if(cookie != null) {
            // Xóa session
            sessionRepository.delete(cookie.getValue());

            // Xet lại cookie
            cookie.setValue(null);
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
//        Cookie cookie = new Cookie("websession", "");
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
    }

    public String getCookies(HttpServletRequest request) {
        // Lấy token từ cookie
        String token = null;
        Cookie cookie = WebUtils.getCookie(request, "websession");
        if (cookie != null) {
            token = cookie.getValue();
        }
        return token;
    }
}
