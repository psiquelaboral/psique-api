package com.psiquelaboral.psique.employee.application;

import com.psiquelaboral.psique.auth.infrastructure.springsecurity.jwt.JWTService;
import com.psiquelaboral.psique.employee.domain.dao.IEmployeeDao;
import com.psiquelaboral.psique.employee.domain.model.Employee;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class EmployeeService {

    private final IEmployeeDao<String> employeeDao;
    private final JWTService jwtService;

    public void createEmployeeUseCase(Employee employee) {
        employee.setCreatedAt(LocalDateTime.now());
        this.employeeDao.create(employee);

        String token = this.jwtService.generateToken(employee);
        employee.setToken(token + "+employee");
        this.employeeDao.update(employee);
    }

    public Employee getByIdUseCase(String employeeId) {
        return this.employeeDao.getById(employeeId);
    }

    public List<Employee> listByCompany(String companyId) {
        return this.employeeDao.listByCompany(companyId);
    }
}
