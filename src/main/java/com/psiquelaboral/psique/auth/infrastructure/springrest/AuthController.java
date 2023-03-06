package com.psiquelaboral.psique.auth.infrastructure.springrest;

import com.psiquelaboral.psique.auth.domain.model.AuthRequest;
import com.psiquelaboral.psique.auth.domain.model.AuthResponse;
import com.psiquelaboral.psique.auth.infrastructure.springsecurity.jwt.JWTService;
import com.psiquelaboral.psique.user.application.IPsiqueUserService;
import com.psiquelaboral.psique.user.application.PsiqueUserService;
import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final IPsiqueUserService userService;
    private final JWTService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){

        //Get the current user
        PsiqueUser authenticatedUser = this.userService.getByEmail(authRequest.getEmail());
        if(authenticatedUser == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
        }

        //Generate JWT with authenticated user
        String token = this.jwtService.generateToken(authenticatedUser);

        //Create the response
        AuthResponse response = AuthResponse.builder()
                .accessToken(token)
                .build();

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> signup(){
        return null;
    }

}
