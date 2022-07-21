package com.example.basic.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("bob").password(passwordEncoder().encode("123")).roles("USER")
                .and()
                .withUser("anna").password(passwordEncoder().encode("123")).roles("USER", "EDITOR", "ADMIN")
                .and()
                .withUser("alice").password(passwordEncoder().encode("123")).roles("USER", "EDITOR");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/profile").hasRole("USER")
                    .antMatchers("/admin/blogs").hasAnyRole("EDITOR", "ADMIN")
                    .antMatchers("/admin/users").hasRole("ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/process-login")
                    .defaultSuccessUrl("/")
    //                .usernameParameter("name") Sử dụng nếu parameter khác default username
    //                .passwordParameter("pass") Sử dụng nếu parameter khác default password
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                .and()
                    .httpBasic();
    }
}
