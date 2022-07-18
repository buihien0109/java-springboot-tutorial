package com.example.sessioncookie.security;


import com.example.sessioncookie.exception.ErrorMessage;
import com.example.sessioncookie.model.User;
import com.example.sessioncookie.repository.RoleRepository;
import com.example.sessioncookie.repository.SessionRepository;
import com.example.sessioncookie.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class SecurityFilter implements Filter {

    private final RoleRepository roleRepository;
    private final SessionRepository sessionRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Lấy ra route hiện tại
        String route = httpRequest.getRequestURI();

        // Kiểm tra route có cần đăng nhập hay không
        List<String> routes = roleRepository.getAllRoutes();

        // Nếu route không trong danh sách cần đăng nhập  --> pass
        if (!routes.contains(route)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Những kiểm tra cần phải đăng nhập
        // Lấy thông tin về cookie
        Cookie cookie = WebUtils.getCookie((HttpServletRequest) request, "websession");

        // Nếu cookie = null --> User chưa đăng nhập --> Error
        if (cookie == null) {
            ErrorMessage message = new ErrorMessage(HttpStatus.UNAUTHORIZED, "Bạn cần đăng nhập");
            httpResponse.addHeader("Content-type", "application/json");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.getWriter().write(Utils.convertObjectToJson(message));
            return;
        }

        // Lấy token từ cookie
        String token = cookie.getValue();
        User user = sessionRepository.findByKey(token);

        // Kiểm tra nếu user có role ADMIN --> pass
        if (user.hasRole("ADMIN")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Kiểm tra nếu user có quyền truy cập route hay không
        if (!user.canPassRoute(route)) {
            ErrorMessage message = new ErrorMessage(HttpStatus.FORBIDDEN, "Bạn không có quyền");
            httpResponse.addHeader("Content-type", "application/json");
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.getWriter().write(Utils.convertObjectToJson(message));
            return;
        }

        filterChain.doFilter(request, response);
    }
}
