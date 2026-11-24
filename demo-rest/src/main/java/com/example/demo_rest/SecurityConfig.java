package com.example.demo_rest;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                auth -> auth.requestMatchers("/uri").authenticated()
                        .anyRequest().permitAll()

        ).csrf(csrf -> csrf.disable());

        return http.build();
    }
}
