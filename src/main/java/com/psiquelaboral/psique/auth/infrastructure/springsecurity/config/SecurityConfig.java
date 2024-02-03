package com.psiquelaboral.psique.auth.infrastructure.springsecurity.config;

import com.psiquelaboral.psique.auth.infrastructure.springsecurity.error.JWTAccessDeniedHandler;
import com.psiquelaboral.psique.auth.infrastructure.springsecurity.error.JWTAuthenticationEntryPoint;
import com.psiquelaboral.psique.auth.infrastructure.springsecurity.filter.JWTTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
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

        //Set up the session management
        http.sessionManagement(management -> management
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        //Disable un used features and set up CORS
        http
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable);

        // Exception handling
        http.exceptionHandling(handling -> handling
            .accessDeniedHandler(jwtAccessDeniedHandler)
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        );

        // JWT Filter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // Secure routes
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers(
                HttpMethod.GET,
                "/quiz/employee/{id}",
                "/employee/{employeeId}"
            ).hasAnyRole(ROLE_EMPLOYEE)
            .requestMatchers("/answer/**").hasAnyRole(ROLE_EMPLOYEE)
            .requestMatchers("/auth/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/user/signup").permitAll()
            .requestMatchers("/user/**", "/employee/**", "/company/**").hasAnyRole(ROLE_RH)
            .requestMatchers("/quiz/**").hasAnyRole(ROLE_RH)
            .requestMatchers(HttpMethod.POST, "/quiz/**").hasAnyRole(ROLE_ADMIN)
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(this.userDetailsService)
            .passwordEncoder(this.passwordEncoder());

        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

}
