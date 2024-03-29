package com.psiquelaboral.psique.employee.infrastructure.springrest;

import com.psiquelaboral.psique.auth.application.IAuthUserManager;
import com.psiquelaboral.psique.auth.domain.model.AuthenticatedUser;
import com.psiquelaboral.psique.employee.application.EmployeeService;
import com.psiquelaboral.psique.employee.domain.model.Employee;
import com.psiquelaboral.psique.shared.infrastructure.openapi.DocumentedRestController;
import com.psiquelaboral.psique.user.domain.model.PsiqueUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RequiredArgsConstructor
@DocumentedRestController
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

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Employee> getBYId(@PathVariable String employeeId) {
        AuthenticatedUser authenticatedUser = this.authUserManager.getAuthUser();
        String requesterId = authenticatedUser.getId();

        PsiqueUser requester = this.authUserManager.whoAmI();

        //the employees only have access of their own data
        if (!requesterId.equals(employeeId) && requester == null) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "As employee you only have access to your own data"
            );
        }

        Employee employee = this.employeeService.getByIdUseCase(employeeId);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/company/employee/{companyId}")
    public ResponseEntity<List<Employee>> createEmployee(@PathVariable String companyId) {
        PsiqueUser requester = this.authUserManager.whoAmI();

        if (!requester.getCompanyId().equals(companyId)) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "You don have access to another employee companies"
            );
        }

        var employees = this.employeeService.listByCompany(companyId);
        return ResponseEntity.ok(employees);
    }
}
