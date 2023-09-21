package com.psiquelaboral.psique.company.domain.dao;

import com.psiquelaboral.psique.company.domain.model.Company;

public interface ICompanyDao<K> {
    void create(Company company);

    Company getById(K companyId);
}
