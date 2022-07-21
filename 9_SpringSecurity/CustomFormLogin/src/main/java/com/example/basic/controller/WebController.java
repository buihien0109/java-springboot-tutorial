package com.example.basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {

    // Vào trang chủ
    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    // Vào trang login
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    // Vào trang profile -> Cần quyền USER
    @GetMapping("/profile")
    public String getProfilePage() {
        return "profile";
    }

    // Xử lý Error -> Trả về Template
    @GetMapping(value = "/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/403";
            } else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
                return "error/401";
            }
        }

        return "error/error";
    }
}
