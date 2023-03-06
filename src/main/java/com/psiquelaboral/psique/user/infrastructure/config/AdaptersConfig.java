package com.psiquelaboral.psique.user.infrastructure.config;

import com.psiquelaboral.psique.user.application.IPsiqueUserService;
import com.psiquelaboral.psique.user.application.PsiqueUserService;
import com.psiquelaboral.psique.user.domain.dao.IPsiqueUserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdaptersConfig {

    @Bean
    IPsiqueUserService psiqueUserService(final IPsiqueUserDao userDao,
                                         final PasswordEncoder passwordEncoder){
        return new PsiqueUserService(userDao, passwordEncoder);
    }


}
