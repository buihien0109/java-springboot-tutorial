package com.example.basic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceCustom userDetailsServiceCustom;

    @Autowired
    private AuthenticationEntryPointCustom authenticationEntryPointCustom;

    @Autowired
    private AuthorizationFilterCustom authorizationFilterCustom;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceCustom).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                    .antMatchers("/", "/auth/login").permitAll()
                    .antMatchers("/profile").hasRole("USER")
                    .antMatchers("/admin/blogs").hasAnyRole("EDITOR", "ADMIN")
                    .antMatchers("/admin/users").hasRole("ADMIN")
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPointCustom)
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("TECHMASTER_SESSION")
                    .permitAll()
                .and()
                    .addFilterBefore(authorizationFilterCustom, UsernamePasswordAuthenticationFilter.class);
    }
}
