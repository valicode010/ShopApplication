package com.sda.company.service.implementation;

import com.sda.company.exception.CompanyNotFoundException;
import com.sda.company.exception.EmployeeNotFoundException;
import com.sda.company.model.Company;
import com.sda.company.model.Employee;
import com.sda.company.repository.EmployeeRepository;
import com.sda.company.service.CompanyService;
import com.sda.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public String populate(List<Employee> employeeList) {
        List<Employee> result = (List<Employee>) employeeRepository.saveAll(employeeList);

        if (result.isEmpty()) {
            return "There is a problem";
        } else {
            return "The list has been created";
        }
    }

    @Override
    public Employee hired(Integer employeeId, Integer companyId) {

        Company company = companyService.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id <" + companyId + "> not found"));

        return employeeRepository.findById(employeeId).map(employee -> {
            employee.setCompany(company);
            employee.setHired(true);

            return employeeRepository.save(employee);
        }).orElseThrow(() -> new EmployeeNotFoundException("Employee with id <" + employeeId + "> not found"));
    }
}
