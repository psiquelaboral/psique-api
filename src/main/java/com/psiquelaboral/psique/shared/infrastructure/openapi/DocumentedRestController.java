package com.psiquelaboral.psique.shared.infrastructure.openapi;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@CrossOrigin
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "401",
        description = "Requested not authenticated",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ProblemDetail.class)
        )
    ),
    @ApiResponse(
        responseCode = "403",
        description = "The requester have not permission",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ProblemDetail.class)
        )
    )
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DocumentedRestController {
}
