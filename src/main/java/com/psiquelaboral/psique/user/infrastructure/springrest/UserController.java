package com.psiquelaboral.psique.user.infrastructure.springrest;

import com.psiquelaboral.psique.user.application.IPsiqueUserService;
import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import com.psiquelaboral.psique.user.infrastructure.mapper.PsiqueUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final IPsiqueUserService userService;
    private final PsiqueUserMapper userMapper;

    @PostMapping("/user/signup")
    public ResponseEntity<PsiqueUser> signup(@RequestBody PsiqueUser user) {
        this.userService.signup(user);
        return ResponseEntity.ok(this.userMapper.toUserSummary(user));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<PsiqueUser> getUserById(@PathVariable String id) {
        PsiqueUser user = this.userService.getById(id);
        return ResponseEntity.ok(this.userMapper.toUserSummary(user));
    }

    @GetMapping("/user")
    public ResponseEntity<List<PsiqueUser>> listAll() {
        List<PsiqueUser> users = this.userService.listAll();

        var usersSummary = users.stream()
                .map(this.userMapper::toUserSummary)
                .collect(Collectors.toList());

        return ResponseEntity.ok(usersSummary);
    }
}
