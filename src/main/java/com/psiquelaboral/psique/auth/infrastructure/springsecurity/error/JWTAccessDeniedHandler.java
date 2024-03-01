package com.psiquelaboral.psique.auth.infrastructure.springsecurity.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psiquelaboral.psique.auth.domain.model.AuthErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        // Set 403 http code to response and add JSON as content type
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        //Create AuthError response with models
        AuthErrorResponse error = AuthErrorResponse.builder()
            .title("Forbidden")
            .status(403)
            .details(accessDeniedException.getMessage())
            .build();

        //Write the response with JSON format
        String errorAsJson = new ObjectMapper().writeValueAsString(error);
        response.getWriter().write(errorAsJson);
    }

}
