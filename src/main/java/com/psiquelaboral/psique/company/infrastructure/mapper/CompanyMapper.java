package com.psiquelaboral.psique.company.infrastructure.mapper;

import com.psiquelaboral.psique.company.domain.model.Company;
import com.psiquelaboral.psique.company.infrastructure.springdata.entity.CompanyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company toModel(CompanyEntity entity);

    CompanyEntity toEntity(Company model);
}
