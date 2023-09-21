package com.psiquelaboral.psique.shared.infrastructure;

import com.psiquelaboral.psique.answer.application.AnswerService;
import com.psiquelaboral.psique.answer.application.IAnswerService;
import com.psiquelaboral.psique.answer.domain.dao.IAnswerDao;
import com.psiquelaboral.psique.auth.infrastructure.springsecurity.jwt.JWTService;
import com.psiquelaboral.psique.company.application.CompanyService;
import com.psiquelaboral.psique.company.domain.dao.ICompanyDao;
import com.psiquelaboral.psique.employee.application.EmployeeService;
import com.psiquelaboral.psique.employee.domain.dao.IEmployeeDao;
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
    public IPsiqueUserService psiqueUserService(final IPsiqueUserDao<String> userDao,
                                                final CompanyService companyService,
                                                final PasswordEncoder passwordEncoder) {
        return new PsiqueUserService(userDao, companyService, passwordEncoder);
    }

    @Bean
    public EmployeeService employeeService(final IEmployeeDao<String> employeeDao, final JWTService jwtService) {
        return new EmployeeService(employeeDao, jwtService);
    }

    @Bean
    public CompanyService companyService(final ICompanyDao<String> companyDao) {
        return new CompanyService(companyDao);
    }

    @Bean
    public IQuizService quizService(final IQuizDao<String> quizDao) {
        return new QuizService(quizDao);
    }

    @Bean
    public IAnswerService<String> answerService(final IAnswerDao<String> answerDao) {
        return new AnswerService(answerDao);
    }
}
