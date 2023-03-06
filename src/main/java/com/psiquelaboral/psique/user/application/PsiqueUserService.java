package com.psiquelaboral.psique.user.application;

import com.psiquelaboral.psique.user.domain.dao.IPsiqueUserDao;
import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class PsiqueUserService implements IPsiqueUserService {

    private final IPsiqueUserDao psiqueUserDao;
    private final PasswordEncoder passwordEncoder;

    /**
     * Create the user and all the process
     * to apply the signup on the application.
     * @param user The information of the new user
     */
    public void signup(PsiqueUser user){
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        psiqueUserDao.create(user);
    }
}
