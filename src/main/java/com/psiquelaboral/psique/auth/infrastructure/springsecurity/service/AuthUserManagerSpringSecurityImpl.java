package com.psiquelaboral.psique.auth.infrastructure.springsecurity.service;

import com.psiquelaboral.psique.auth.application.IAuthUserManager;
import com.psiquelaboral.psique.auth.domain.model.AuthenticatedUser;
import com.psiquelaboral.psique.user.application.IPsiqueUserService;
import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserManagerSpringSecurityImpl implements IAuthUserManager {

    private final IPsiqueUserService userService;

    @Override
    public PsiqueUser whoAmI() {
        AuthenticatedUser requester = this.getAuthUser();
        return this.userService.getById(requester.getId());
    }

    @Override
    public AuthenticatedUser getAuthUser() {
        Authentication ctx = SecurityContextHolder.getContext().getAuthentication();
        PsiqueUser user = (PsiqueUser) ctx.getPrincipal();
        return new AuthenticatedUser(user.getId(), user.getEmail());
    }
}
