package com.example.basic.controller;

import com.example.basic.security.AuthenticationFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Slf4j
public class WebController {

    private final AuthenticationFacade authenticationFacade;

    public WebController(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping("hello")
    public ResponseEntity<?> getHello() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("profile")
    public ResponseEntity<?> getProfile() {
        return ResponseEntity.ok("Profile");
    }

    @GetMapping("admin")
    public ResponseEntity<?> getAdmin() {
        return ResponseEntity.ok("admin");
    }

    // Lấy thông tin người dùng sử dụng SecurityContextHolder
    @GetMapping("user-info")
    public ResponseEntity<?> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication);
    }

    // Lấy thông tin người dùng sử dụng Principal object
    @GetMapping("user-info-1")
    public ResponseEntity<?> getUserInfo1(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    // Lấy thông tin người dùng sử dụng Authentication
    @GetMapping("user-info-2")
    public ResponseEntity<?> getUserInfo2(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(userDetails);
    }

    // Lấy thông tin người dùng sử dụng Custom Interface
    @GetMapping("user-info-3")
    public ResponseEntity<?> getUserInfo3() {
        Authentication authentication = authenticationFacade.getAuthentication();
        return ResponseEntity.ok(authentication);
    }
}
