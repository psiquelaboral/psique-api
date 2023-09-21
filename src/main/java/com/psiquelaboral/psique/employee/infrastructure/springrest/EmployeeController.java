package com.psiquelaboral.psique.employee.infrastructure.springrest;

import com.psiquelaboral.psique.auth.application.IAuthUserManager;
import com.psiquelaboral.psique.employee.application.EmployeeService;
import com.psiquelaboral.psique.employee.domain.model.Employee;
import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final IAuthUserManager authUserManager;

    @PostMapping("/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        PsiqueUser requester = this.authUserManager.whoAmI();
        employee.setCompanyId(requester.getCompanyId());
        this.employeeService.createEmployeeUseCase(employee);
        return ResponseEntity.ok(employee);
    }
}
