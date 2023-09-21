package com.psiquelaboral.psique.employee.application;

import com.psiquelaboral.psique.employee.domain.dao.IEmployeeDao;
import com.psiquelaboral.psique.employee.domain.model.Employee;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class EmployeeService {

    private final IEmployeeDao<String> employeeDao;

    public void createEmployeeUseCase(Employee employee) {
        employee.setCreatedAt(LocalDateTime.now());
        this.employeeDao.create(employee);
    }
}
