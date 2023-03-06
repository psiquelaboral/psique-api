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

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .cors()
          .and()
        .csrf().disable()
        .exceptionHandling()
          .accessDeniedHandler(jwtAccessDeniedHandler)
          .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
          .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
          .authorizeRequests()
            .antMatchers("/**").permitAll()
        .and()
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
