package com.psiquelaboral.psique.auth.application;

import com.psiquelaboral.psique.auth.domain.model.AuthenticatedUser;
import com.psiquelaboral.psique.user.domain.model.PsiqueUser;

public interface IAuthUserManager {
    PsiqueUser whoAmI();

    AuthenticatedUser getAuthUser();
}
