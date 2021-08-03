package com.sda.company.service.implementation;

import com.sda.company.exception.CompanyNotFoundException;
import com.sda.company.model.Company;
import com.sda.company.repository.CompanyRepository;
import com.sda.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company create(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAll() {
        return (List<Company>) companyRepository.findAll();
    }

    @Override
    public List<Company> getAllPaginated(Integer pageNumber, Integer pageSize, String shortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(shortBy));
        Page<Company> companyPage = companyRepository.findAll(pageable);
        return companyPage.getContent();
    }

    @Override
    public Company findByName(String name) {
        return companyRepository.findByName(name)
                .orElseThrow(() -> new CompanyNotFoundException("Company with name <" + name + "> not found"));
    }

    @Override
    public String populate(List<Company> companiesList) {
        List<Company> result = (List<Company>) companyRepository.saveAll(companiesList);
        if (result.isEmpty()) {
            System.out.println();
            return "There is a problem";
        } else {
            return "The list has been created";
        }
    }

    @Override
    public void deleteCompanyById(Integer id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            companyRepository.deleteById(id);
        } else {
            throw new CompanyNotFoundException("Company with name <" + id + "> not found");
        }
    }

    @Override
    public void deleteByRegistrationNumber(String registrationNumber) {
        Optional<Company> company = companyRepository.findByRegistrationNumber(registrationNumber);

        if (company.isPresent()) {
            companyRepository.deleteById(company.get().getId());
        } else {
            throw new CompanyNotFoundException("Company with name <" + registrationNumber + "> not found");
        }
    }

    @Override
    public Optional<Company> findById(Integer id) {
        return companyRepository.findById(id);
    }
}

