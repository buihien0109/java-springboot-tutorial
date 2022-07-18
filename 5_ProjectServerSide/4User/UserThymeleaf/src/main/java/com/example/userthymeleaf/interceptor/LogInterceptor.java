package com.example.userthymeleaf.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Lấy thời gian hiện tại
        LocalTime localTime = LocalTime.now();

        // Format thời gian theo định dạng thời gian đã chỉ định
        String localTimeStr = localTime.format(DateTimeFormatter.ofPattern("hh:mm:ss"));

        // In ra định dạng request tương ứng : Time - HTTP Request - URL
        log.info(localTimeStr + " - " + request.getMethod() + " - " + request.getRequestURI() + " - " + request.getQueryString());

        return true;
    }
}
