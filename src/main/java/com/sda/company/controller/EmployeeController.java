package com.sda.company.controller;


import com.sda.company.components.CustomFakerEmployee;
import com.sda.company.model.Employee;
import com.sda.company.service.EmployeeService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CustomFakerEmployee customFakerEmployee;

    @Autowired
    public EmployeeController(EmployeeService employeeService, CustomFakerEmployee customFakerEmployee) {
        this.employeeService = employeeService;
        this.customFakerEmployee = customFakerEmployee;
    }

    @GetMapping("/populateWithEmployee")
    public ResponseEntity<String> populate() {
        return ResponseEntity.ok(employeeService.populate(customFakerEmployee.createDummyEmployeeList()));
    }

    @PutMapping("/hire")
    public ResponseEntity<Employee> hireEmployee(@RequestParam @NotNull Integer employeeId,
                                                 @RequestParam @NotNull Integer companyId) {

        return ResponseEntity.ok(employeeService.hired(employeeId, companyId));
    }
}
