package com.psiquelaboral.psique.user.domain.dao;

import com.psiquelaboral.psique.user.domain.model.PsiqueUser;

public interface IPsiqueUserDao {
  PsiqueUser getByEmail(String email);
  void create(PsiqueUser user);
}
