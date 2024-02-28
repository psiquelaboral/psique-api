package com.psiquelaboral.psique.user.infrastructure.springrest;

import com.psiquelaboral.psique.shared.infrastructure.openapi.DocumentedRestController;
import com.psiquelaboral.psique.user.application.IPsiqueUserService;
import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import com.psiquelaboral.psique.user.infrastructure.mapper.PsiqueUserMapper;
import com.psiquelaboral.psique.user.infrastructure.springrest.docannotion.SignUpOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@DocumentedRestController
public class UserController {

    private final IPsiqueUserService userService;
    private final PsiqueUserMapper userMapper;

    @SignUpOperation
    @PostMapping("/user/signup")
    public ResponseEntity<PsiqueUser> signup(@RequestBody PsiqueUser user) {
        this.userService.signup(user);
        return ResponseEntity.ok(this.userMapper.toUserSummary(user));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<PsiqueUser> getUserById(@PathVariable String id) {
        PsiqueUser user = this.userService.getById(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + id);
        }
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
