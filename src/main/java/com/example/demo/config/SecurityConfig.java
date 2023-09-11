package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(antMatcher("/api/auth/**")).permitAll();
                    auth.requestMatchers(antMatcher("/api/city/**")).permitAll();
                    auth.requestMatchers(antMatcher("/api/industry/**")).permitAll();
                    auth.requestMatchers(antMatcher("/api/workfield/**")).permitAll();
                    auth.requestMatchers(antMatcher("/api/job/count")).permitAll();
                    auth.requestMatchers(antMatcher("/api/employer/count")).permitAll();
                    auth.requestMatchers(antMatcher("/api/job/all")).permitAll();
                    auth.requestMatchers(antMatcher("/api/job/find")).permitAll();
                    auth.requestMatchers(antMatcher("/api/job/posted_date/**")).permitAll();
                    auth.requestMatchers(antMatcher("/api/job/search")).permitAll();
                    auth.anyRequest().authenticated();
                });
        http
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.cors();
        return http.build();
    }
}
