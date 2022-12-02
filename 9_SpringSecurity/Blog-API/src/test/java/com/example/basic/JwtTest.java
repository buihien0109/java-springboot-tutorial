package com.example.basic;

import com.example.basic.security.JwtUtils;
import com.example.basic.service.UserService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpSession;
import java.util.Date;

@SpringBootTest
public class JwtTest {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    private final MockHttpSession session = new MockHttpSession();

    @Test
    void generate_token_test() {
        UserDetails userDetails = userService.loadUserByUsername("hien@techmaster.vn");
        System.out.println(userDetails);

        String token = jwtUtils.generateToken(userDetails);
        System.out.println(token);
    }

    @Test
    void time_jwt_token_test() {
        // Ngày tạo là thời điểm hiện tại
        Date createdDate = new Date(System.currentTimeMillis());
        System.out.println(createdDate);

        // Ngày hết hạn là vào 1 tuần sau đó
        Date expirationDate = new Date(System.currentTimeMillis() + 604800 * 1000);
        System.out.println(expirationDate);
    }

    @Test
    void save_token_to_cookie_test() {
        UserDetails userDetails = userService.loadUserByUsername("hien@techmaster.vn");
        System.out.println(userDetails);

        String token = jwtUtils.generateToken(userDetails);
        System.out.println(token);

        session.setAttribute("JWT_TOKEN_TEST", token); // eyJhbGciOiJIUzUxMiJ9
    }

    @Test
    void get_token_from_cookie_test() {
        String token = (String) session.getAttribute("JWT_TOKEN_TEST");
        System.out.println(token);
    }

    @Test
    void delete_token_from_cookie_test(HttpSession session) {
        session.invalidate();
        session.setAttribute("JWT_TOKEN_TEST", null);
    }

    @Test
    void get_claims_from_token_test() {
        UserDetails userDetails = userService.loadUserByUsername("hien@techmaster.vn");
        System.out.println(userDetails);

        String token = jwtUtils.generateToken(userDetails);
        System.out.println(token);


        Claims claims = jwtUtils.getClaimsFromToken(token);
        System.out.println(claims.toString());
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getExpiration());
    }

    @Test
    void check_jwt_token_expired_test() {
        UserDetails userDetails = userService.loadUserByUsername("hien@techmaster.vn");
        System.out.println(userDetails);

        String token = jwtUtils.generateToken(userDetails);
        System.out.println(token);

        Claims claims = jwtUtils.getClaimsFromToken(token);

        Date expirationDate = claims.getExpiration();

        Date dateTest = new Date(System.currentTimeMillis() + 604800 * 1000 * 2);
        Date dateTest1 = new Date(System.currentTimeMillis() - 604800 * 1000);

        System.out.println(expirationDate.before(dateTest));
        System.out.println(expirationDate.after(dateTest1));
    }
}
