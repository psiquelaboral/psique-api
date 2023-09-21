package com.psiquelaboral.psique.employee.domain.dao;

import com.psiquelaboral.psique.employee.domain.model.Employee;

import java.util.List;

public interface IEmployeeDao<K> {
    void create(Employee employee);

    void update(Employee employee);

    Employee getById(K id);

    Employee getByEmail(String email);

    List<Employee> listByCompany(K companyId);
}
