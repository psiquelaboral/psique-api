package com.psiquelaboral.psique.auth.infrastructure.springsecurity.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psiquelaboral.psique.auth.domain.model.AuthErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;


@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        // Set 401 http code to response and add JSON as content type
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        //Create AuthError response with models
        AuthErrorResponse error = AuthErrorResponse.builder()
                .title("Unauthorized")
                .status(401)
                .details(authException.getMessage())
                .build();

        //Write the response with JSON format
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, error);
        out.flush();
    }

}
