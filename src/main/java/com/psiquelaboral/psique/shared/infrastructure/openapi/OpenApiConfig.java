package com.psiquelaboral.psique.shared.infrastructure.openapi;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        version = "v1",
        title = "Psique API",
        description = "API to handler the backend of Psique Laboral",
        contact = @Contact(name = "Joaquin Coronado", email = "joaquincr@hotmail.com", url = "https://psiquelaboral.com/")
    ),
    servers = {
        @Server(url = "https://p01--psique-api--qpfx26t9ms8m.code.run/", description = "Production"),
        @Server(url = "http://localhost:8080/", description = "Local")
    }
)
@SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class OpenApiConfig {
}
