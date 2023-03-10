package com.psiquelaboral.psique.user.application;

import com.psiquelaboral.psique.user.domain.model.PsiqueUser;

import java.util.List;

public interface IPsiqueUserService {
    void signup(PsiqueUser user);

    PsiqueUser getByEmail(String email);

    PsiqueUser getById(String id);

    List<PsiqueUser> listAll();
}
