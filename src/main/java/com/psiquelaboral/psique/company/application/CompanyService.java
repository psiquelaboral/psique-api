package com.psiquelaboral.psique.company.application;

import com.psiquelaboral.psique.company.domain.dao.ICompanyDao;
import com.psiquelaboral.psique.company.domain.model.Company;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CompanyService {

    private final ICompanyDao<String> companyDao;

    public void createUseCase(Company company) {
        company.setCreatedAt(LocalDateTime.now());
        this.companyDao.create(company);
    }

    public Company getByIdUseCase(String companyId) {
        return this.companyDao.getById(companyId);
    }
}
