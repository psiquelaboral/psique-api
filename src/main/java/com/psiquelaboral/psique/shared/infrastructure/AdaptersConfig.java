package com.psiquelaboral.psique.shared.infrastructure;

import com.psiquelaboral.psique.answer.application.AnswerService;
import com.psiquelaboral.psique.answer.application.IAnswerService;
import com.psiquelaboral.psique.answer.domain.dao.IAnswerDao;
import com.psiquelaboral.psique.quiz.application.IQuizService;
import com.psiquelaboral.psique.quiz.application.QuizService;
import com.psiquelaboral.psique.quiz.domain.dao.IQuizDao;
import com.psiquelaboral.psique.user.application.IPsiqueUserService;
import com.psiquelaboral.psique.user.application.PsiqueUserService;
import com.psiquelaboral.psique.user.domain.dao.IPsiqueUserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdaptersConfig {

    @Bean
    public IPsiqueUserService psiqueUserService(final IPsiqueUserDao userDao,
                                                final PasswordEncoder passwordEncoder) {
        return new PsiqueUserService(userDao, passwordEncoder);
    }

    @Bean
    public IQuizService quizService(final IQuizDao quizDao) {
        return new QuizService(quizDao);
    }

    @Bean
    public IAnswerService<String> answerService(final IAnswerDao<String> answerDao) {
        return new AnswerService(answerDao);
    }

}
