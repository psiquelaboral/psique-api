package com.psiquelaboral.psique.auth.infrastructure.springsecurity.config;

import com.psiquelaboral.psique.auth.infrastructure.springsecurity.error.JWTAccessDeniedHandler;
import com.psiquelaboral.psique.auth.infrastructure.springsecurity.error.JWTAuthenticationEntryPoint;
import com.psiquelaboral.psique.auth.infrastructure.springsecurity.filter.JWTTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JWTAccessDeniedHandler jwtAccessDeniedHandler;
    private final JWTTokenFilter jwtFilter;

    private static final String[] ROLE_EMPLOYEE = {"GOD", "ADMIN", "RH", "EMPLOYEE"};
    private static final String[] ROLE_RH = {"GOD", "ADMIN", "RH"};
    private static final String[] ROLE_ADMIN = {"GOD", "ADMIN"};


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(handling -> handling
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/user/**").hasAnyRole(ROLE_RH)
                        .requestMatchers("/answer/**").hasAnyRole(ROLE_EMPLOYEE)
                        .requestMatchers(HttpMethod.GET, "/quiz/**").hasAnyRole(ROLE_EMPLOYEE)
                        .requestMatchers(HttpMethod.POST, "/quiz/**").hasAnyRole(ROLE_ADMIN)
                )
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder())
                .and()
                .build();
    }

}
