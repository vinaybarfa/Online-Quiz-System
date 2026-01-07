package com.vinay.OnlineQuizapp.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.requestMatchers("/question/**").authenticated()
                        .requestMatchers("/create/**").authenticated()
                        .requestMatchers("/student/register", "/student/login").permitAll()
                        .requestMatchers("/student/**").authenticated()
                        .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf.disable()) // Only for stateless APIs
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .formLogin(formLogin -> formLogin.loginPage("/http://127.0.0.1:5500/html/login.html")); // Explicitly disable the login form

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
