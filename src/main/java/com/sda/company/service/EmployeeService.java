package com.sda.company.service;

import com.sda.company.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    String populate (List<Employee> employeeList);

    Employee hired(Integer employeeId, Integer companyId);

    Optional<Employee> findById(Integer id);

}
