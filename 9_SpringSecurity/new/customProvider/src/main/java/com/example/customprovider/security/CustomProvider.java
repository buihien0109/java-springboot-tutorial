package com.example.customprovider.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class CustomProvider implements AuthenticationProvider {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private List<User> users = new ArrayList<>(List.of(
            new User("user", passwordEncoder.encode("111"), List.of(
                    new SimpleGrantedAuthority("ROLE_USER"))
            ),
            new User("admin", passwordEncoder.encode("111"), List.of(
                    new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_ADMIN"))
            ),
            new User("author", passwordEncoder.encode("111"), List.of(
                    new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_AUTHOR"))
            )
    ));

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        log.info("username : {}", username);
        log.info("password : {}", password);


        Optional<User> userOptional = users.stream()
                .map(u -> {
                    log.info("Match password : {}", passwordEncoder.matches(password, u.getPassword()));
                    return u;
                })
                .filter(u -> u.getUsername().equals(username) && passwordEncoder.matches(password, u.getPassword()))
                .findAny();

        if (userOptional.isEmpty()) {
            throw new BadCredentialsException("User authentication failed!!!!");
        }

        User user = userOptional.get();
        log.info("user : {}", user);
        return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
