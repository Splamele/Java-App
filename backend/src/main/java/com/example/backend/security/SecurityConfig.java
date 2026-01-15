package com.example.backend.security;

import com.example.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private UsersService usersService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // Bean pour encoder les mots de passe
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean pour AuthenticationManager
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Configuration de la sécurité HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // désactive CSRF pour API REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/api/auth/**", "/images/**").permitAll() // endpoints publics
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")             // accessible uniquement ADMIN
                        .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")   // accessible aux utilisateurs authentifiés
                        .anyRequest().authenticated()                                   // tout le reste nécessite authentification
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
