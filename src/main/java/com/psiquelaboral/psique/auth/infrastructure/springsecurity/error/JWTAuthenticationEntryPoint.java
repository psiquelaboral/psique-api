package com.psiquelaboral.psique.auth.infrastructure.springsecurity.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psiquelaboral.psique.auth.domain.model.AuthError;
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

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        //Create AuthError response from models
        AuthError authError = AuthError
                .builder()
                .code(HttpServletResponse.SC_UNAUTHORIZED)
                .message(authException.getMessage())
                .build();

        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, authError);
        out.flush();
    }

}
