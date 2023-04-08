package com.example.methodsecurity.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMethodSecurity(
        securedEnabled = true
)
@Slf4j
public class SecurityConfig {
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("111")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("111")
//                .roles("USER", "ADMIN")
//                .build();
//
//        UserDetails author = User.withDefaultPasswordEncoder()
//                .username("author")
//                .password("111")
//                .roles("AUTHOR")
//                .build();
//
//        log.info("user : {} - password user : {}", user, user.getPassword());
//        log.info("admin : {} - password admin : {}", admin, admin.getPassword());
//        log.info("author : {} - password author : {}", author, author.getPassword());
//
//        return new InMemoryUserDetailsManager(user, admin, author);
//    }

    private final List<UserDetails> userDetails = new ArrayList<>(List.of(
            new User("user", new BCryptPasswordEncoder().encode("111"), List.of(new SimpleGrantedAuthority("ROLE_USER"))),
            new User("admin", new BCryptPasswordEncoder().encode("111"), List.of(
                    new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_ADMIN"))
            ),
            new User("author", new BCryptPasswordEncoder().encode("111"), List.of(new SimpleGrantedAuthority("ROLE_AUTHOR")))
    ));

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userDetails.stream().filter(user -> user.getUsername().equals(username))
                        .findFirst()
                        .orElseThrow(() -> {
                            throw new UsernameNotFoundException("Not found user");
                        });
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/hello").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/hello", true)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/hello")
                .permitAll()
                .and()
                .authenticationProvider(authenticationProvider());
        return http.build();
    }
}

