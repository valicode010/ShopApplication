package com.sda.company.components;

import com.github.javafaker.Faker;
import com.sda.company.model.Company;

import java.util.ArrayList;
import java.util.List;

// aici se fac companiile fake pentru a popula baza de date
public class CustomFakerCompany {

    public List<Company> createDummyCompanyList() {
        Faker faker = new Faker();
        List<Company> companyList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Company company = new Company();
            company.setName(faker.company().name());
            company.setAddress(faker.address().fullAddress());
            company.setPhoneNumber(faker.phoneNumber().phoneNumber());
            company.setEmail(faker.bothify("?????##@gmail.com"));
            company.setRegistrationNumber(String.valueOf(faker.number().randomNumber(11, true)));

            companyList.add(company);
        }
        return companyList;
    }
}
