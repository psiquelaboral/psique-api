package com.psiquelaboral.psique.user.infrastructure.springrest;

import com.psiquelaboral.psique.user.application.IPsiqueUserService;
import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import com.psiquelaboral.psique.user.infrastructure.mapper.PsiqueUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IPsiqueUserService userService;
    private final PsiqueUserMapper userMapper;

    @PostMapping("/signup")
    public ResponseEntity<PsiqueUser> signup(@RequestBody PsiqueUser user){
        this.userService.signup(user);
        return ResponseEntity.ok(this.userMapper.toUserSummary(user));
    }

    public ResponseEntity<?> getUserById(){
        return null;
    }
}
