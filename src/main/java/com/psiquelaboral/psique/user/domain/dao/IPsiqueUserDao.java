package com.psiquelaboral.psique.user.domain.dao;

import com.psiquelaboral.psique.user.domain.model.PsiqueUser;

import java.util.List;

public interface IPsiqueUserDao<K> {
    void create(PsiqueUser user);

    void update(PsiqueUser user);

    PsiqueUser getByEmail(K email);

    PsiqueUser getById(K id);

    List<PsiqueUser> listAll();
}
