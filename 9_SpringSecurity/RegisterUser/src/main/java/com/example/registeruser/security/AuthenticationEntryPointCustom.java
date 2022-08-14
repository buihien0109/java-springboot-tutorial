package com.example.registeruser.security;

import com.example.registeruser.exception.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointCustom implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorMessage message = new ErrorMessage();

        if (authException.getClass().equals(BadCredentialsException.class)) {
            message.setStatus(HttpStatus.UNAUTHORIZED);
            message.setMessage("Tài khoản hoặc mật khẩu không chính xác");
        } else if (authException.getClass().equals(DisabledException.class)) {
            message.setStatus(HttpStatus.UNAUTHORIZED);
            message.setMessage("Tài khoản chưa được kích hoạt");
        } else {
            message.setStatus(HttpStatus.UNAUTHORIZED);
            message.setMessage("Bạn cần đăng nhập");
        }

//        ErrorMessage message = new ErrorMessage(HttpStatus.UNAUTHORIZED, "Bạn cần đăng nhập");

        ObjectMapper objectMapper = new ObjectMapper();
        String messageJSON = objectMapper.writeValueAsString(message);

        response.addHeader("Content-type", "application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(messageJSON);
    }
}
