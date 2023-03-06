package com.psiquelaboral.psique.user.infrastructure.config;

import com.psiquelaboral.psique.user.application.IPsiqueUserService;
import com.psiquelaboral.psique.user.application.PsiqueUserService;
import com.psiquelaboral.psique.user.domain.dao.IPsiqueUserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdaptersConfig {

    @Bean
    IPsiqueUserService psiqueUserService(final IPsiqueUserDao userDao){
        return new PsiqueUserService(userDao);
    }

}
