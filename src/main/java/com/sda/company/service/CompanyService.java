package com.sda.company.service;

import com.sda.company.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CompanyService {

    Company create(Company company);

    List<Company> getAll();

    List<Company> getAllPaginated(Integer pageNumber, Integer pageSize, String shortBy);

    Company findByName(String name);

    String populate (List<Company> companiesList);

    void deleteCompanyById(Integer id);

    void deleteByRegistrationNumber(String registrationNumber);

    Optional<Company> findById(Integer id);
}
