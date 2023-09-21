package com.psiquelaboral.psique.employee.infrastructure.mapper;

import com.psiquelaboral.psique.employee.domain.model.Employee;
import com.psiquelaboral.psique.employee.infrastructure.springdata.entity.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toModel(EmployeeEntity entity);

    EmployeeEntity toEntity(Employee model);
}
