package com.psiquelaboral.psique.user.application;

import com.psiquelaboral.psique.company.application.CompanyService;
import com.psiquelaboral.psique.company.domain.model.Company;
import com.psiquelaboral.psique.user.domain.dao.IPsiqueUserDao;
import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import com.psiquelaboral.psique.user.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class PsiqueUserService implements IPsiqueUserService {

    private final IPsiqueUserDao<String> psiqueUserDao;
    private final CompanyService companyService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Create the user and all the process
     * to apply the signup on the application.
     *
     * @param user The information of the new user
     */
    public void signup(PsiqueUser user) {

        //create main role
        Role role = new Role();
        role.setCreatedAt(LocalDateTime.now());
        role.setName(Role.RoleName.ROLE_RH);
        user.getRoles().add(role);

        //set new values
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        user.setName(user.getName().toLowerCase());

        this.psiqueUserDao.create(user);

        if (user.getCompanyId() == null) {
            //create company
            Company company = new Company();
            company.setRepresentativeId(user.getId());
            company.setCreatedAt(LocalDateTime.now());
            company.setName(user.getName());
            this.companyService.createUseCase(company);

            user.setCompanyId(company.getId());
            this.psiqueUserDao.update(user);
        }
    }

    @Override
    public PsiqueUser getByEmail(String email) {
        return this.psiqueUserDao.getByEmail(email);
    }

    @Override
    public PsiqueUser getById(String id) {
        return this.psiqueUserDao.getById(id);
    }

    @Override
    public List<PsiqueUser> listAll() {
        return this.psiqueUserDao.listAll();
    }

}
