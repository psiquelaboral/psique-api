package com.psiquelaboral.psique.auth.infrastructure.springrest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/")
public class AuthController {

    public ResponseEntity<?> signup(){
        return null;
    }

    public ResponseEntity<?> login(){
        return null;
    }

}
