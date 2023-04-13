package com.example.jwt.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // TODO : Lấy thông tin header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // TODO: Kiểm tra xem header có "Authorization" hoặc header có chứa "Bearer" token hay không

        // TODO: Lấy token từ trong header
        jwt = authHeader.substring(7);

        // TODO: Lấy ra userEmail từ trong token
        userEmail = jwtUtils.extractUsername(jwt);

        // TODO: Kiểm tra userEmail -> Tạo đối tượng xác thực
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Lấy thông tin của user dựa trên userEmail
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);

            // Nếu token hợp lệ (trùng username và chưa hết hạn) -> Tạo đối tượng phân quyền -> Lưu lại
            if (jwtUtils.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
