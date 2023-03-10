package com.psiquelaboral.psique.user.domain.dao;

import com.psiquelaboral.psique.user.domain.model.PsiqueUser;

import java.util.List;

public interface IPsiqueUserDao {
    void create(PsiqueUser user);

    PsiqueUser getByEmail(String email);

    PsiqueUser getById(String id);

    List<PsiqueUser> listAll();
}
