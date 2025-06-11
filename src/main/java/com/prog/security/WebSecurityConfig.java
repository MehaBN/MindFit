package com.prog.security;

import com.prog.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserService userService;

    // Inject UserService using constructor injection with @Lazy to break circular dependency
    public WebSecurityConfig(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .requestMatchers("/", "/home", "/signup", "/login").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN") // Admin-only access
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/userprofile", true)
                .permitAll()
            .and()
            .logout()
                .logoutSuccessUrl("/home")
                .permitAll();

        return http.build();
    }
//----------------

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(userService)  // Use your custom UserDetailsService
                   .passwordEncoder(passwordEncoder())  // Use BCryptPasswordEncoder bean
                   .and()
                   .build();
        
        
        //------------------
        
        
        
        
        //------------------
    }
}
