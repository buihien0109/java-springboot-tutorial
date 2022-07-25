package com.example.sessioncookie.config;

import com.example.sessioncookie.model.User;
import com.example.sessioncookie.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
public class InterceptorConfig implements HandlerInterceptor {

    private final SessionRepository sessionRepository;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Lấy thông tin của user đang đăng nhập
        Cookie cookie = WebUtils.getCookie(request, "websession");
        if (cookie == null) {
            modelAndView.addObject("isLogined", false);
            return;
        }

        // Lấy token từ cookie
        String token = cookie.getValue();
        User user = sessionRepository.findByKey(token);

        if (user != null) {
            modelAndView.addObject("userLogin", user);
            modelAndView.addObject("isLogined", true);
        } else {
            modelAndView.addObject("isLogined", false);
        }
    }
}
