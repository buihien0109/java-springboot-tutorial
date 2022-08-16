package com.example.basic.security;

import com.example.basic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final AuthenticationEntryPointCustom authenticationEntryPointCustom;
    private final AuthorizationFilterCustom authorizationFilterCustom;
    private final AccessDeniedHandlerCustom accessDeniedHandlerCustom;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //    @Bean
    //    public DaoAuthenticationProvider customAuthenticationProvider(){
    //        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    //        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
    //        daoAuthenticationProvider.setUserDetailsService(userService);
    //        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    //        return daoAuthenticationProvider;
    //    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(customAuthenticationProvider());
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                    .antMatchers("/", "/api/auth/**").permitAll()
                    .antMatchers("/profile").hasRole("USER")
                    .antMatchers("/admin/blogs").hasAnyRole("EDITOR", "ADMIN")
                    .antMatchers("/admin/users").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPointCustom)
                    .accessDeniedHandler(accessDeniedHandlerCustom)
                .and()
                    .logout()
                    .logoutUrl("/api/auth/logout")
                    .deleteCookies("MY_SESSION")
                    .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
                    .permitAll()
                .and()
                    .addFilterBefore(authorizationFilterCustom, UsernamePasswordAuthenticationFilter.class);
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/js/**", "/css/**", "/image/**");
//    }
}
