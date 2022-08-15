package com.example.basic.security;

import com.example.basic.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
@AllArgsConstructor
public class AuthorizationFilterCustom extends OncePerRequestFilter {
    private JwtUtils jwtUtils;
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Lấy token từ trong header của request
        String token = jwtUtils.getTokenFromCookie(request);
        if(token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Parse thông tin từ token
        Claims claims = jwtUtils.getClaimsFromToken(token);
        if (claims == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Kiểm tra token đã hết hạn hay chưa
        Date expiration = claims.getExpiration();
        if (expiration.before(new Date())) {
            filterChain.doFilter(request, response);
            return;
        }

        // Lấy thông tin của user dựa trên email
        UserDetails user = userService.loadUserByUsername(claims.getSubject());

        // Tạo đối tượng authentication
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        // Lưu vào trong context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
