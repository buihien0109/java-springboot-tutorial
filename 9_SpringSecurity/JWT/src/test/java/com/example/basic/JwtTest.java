package com.example.basic;

import com.example.basic.security.JwtUtils;
import com.example.basic.security.UserDetailsCustom;
import com.example.basic.security.UserDetailsServiceCustom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
public class JwtTest {

    @Autowired
    private UserDetailsServiceCustom userDetailsServiceCustom;

    @Autowired
    private JwtUtils jwtUtils;

    @Test
    void generate_token_test() {
        UserDetails user = userDetailsServiceCustom.loadUserByUsername("hien@techmaster.vn");

        String token = jwtUtils.generateToken(user);
        System.out.println(token);
    }
}
