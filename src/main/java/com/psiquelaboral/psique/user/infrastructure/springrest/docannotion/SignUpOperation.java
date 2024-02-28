package com.psiquelaboral.psique.user.infrastructure.springrest.docannotion;

import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Operation(
    summary = "Create new user",
    description = """
        Create a new user on the system, this endpoint do encrypt the password assigned to the user and assign by 
        default the role ROLE_RH. \n
        Just the next fields are required:
        * name
        * email
        * password
        """,
    responses = {
        @ApiResponse(
            responseCode = "201",
            description = "Create a new user on the system",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PsiqueUser.class)
            )
        )
    }
)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SignUpOperation {
}
