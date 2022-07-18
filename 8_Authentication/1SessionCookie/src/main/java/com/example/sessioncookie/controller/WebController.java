package com.example.sessioncookie.controller;

import com.example.sessioncookie.model.User;
import com.example.sessioncookie.request.LoginRequest;
import com.example.sessioncookie.service.WebService;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@AllArgsConstructor
public class WebController implements ErrorController {

    private final WebService webService;

    @PostMapping("/api/v1/login")
    public @ResponseBody
    ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        User user = webService.login(request, response);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/api/v1/logout")
    public @ResponseBody
    ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        webService.logout(request, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("message", "Home page");
        return "index";
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
