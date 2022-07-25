package com.example.basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {
    @GetMapping("/")
    public String getIndex(Model model) {
        // Lấy ra thông tin xác thực (có thể có hoặc không)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Trả về thông tin xác thực
        model.addAttribute("authentication", authentication.toString());

        return "index";
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            UserDetails user = (UserDetails) authentication.getPrincipal();
//            model.addAttribute("user", user);
//        } else {
//            model.addAttribute("user", null);
//        }

        // Trả về thông tin xác thực
        model.addAttribute("authentication", authentication.toString());

        return "profile";
    }

    @GetMapping("/test")
    public String getTest() {
        return "test";
    }

    @GetMapping("/api/messages")
    public ResponseEntity<?> getMessage() {
        return ResponseEntity.ok("Hello");
    }

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
