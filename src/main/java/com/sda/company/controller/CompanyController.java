package com.sda.company.controller;

import com.sda.company.components.CustomFakerCompany;
import com.sda.company.model.Company;
import com.sda.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping("/api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final CustomFakerCompany customFakerCompany;

    @Autowired
    public CompanyController(CompanyService companyService,
                             CustomFakerCompany customFakerCompany) {
        this.companyService = companyService;
        this.customFakerCompany = customFakerCompany;
    }

    @PostMapping("/create")
    public ResponseEntity<Company> create(@RequestBody Company company) {
        return ResponseEntity.ok(companyService.create(company));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAll")
    public ResponseEntity<List<Company>> getAll() {
        return ResponseEntity.ok(companyService.getAll());
    }

    @GetMapping("/getAllPaginated")
    public ResponseEntity<List<Company>> getAllPaginated(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "50") Integer pageSize,
            @RequestParam(defaultValue = "name") String shortBy) {
        return ResponseEntity.ok(companyService.getAllPaginated(pageNumber, pageSize, shortBy));
    }

    @GetMapping("/findByName")
    public ResponseEntity<Company> findByName(@RequestParam String name) {
        return ResponseEntity.ok(companyService.findByName(name));
    }

    @GetMapping("/populate")
    public ResponseEntity<String> populate() {
        return ResponseEntity.ok(companyService.populate(customFakerCompany.createDummyCompanyList()));
    }

    @DeleteMapping("/deleteById")
    void deleteById(@RequestParam Integer id) {
        companyService.deleteCompanyById(id);
    }

    @DeleteMapping("/deleteByRegistrationNumber")
    void deleteByRegistrationNumber(@RequestParam String registrationNumber) {
        companyService.deleteByRegistrationNumber(registrationNumber);
    }
}


