package com.sda.company.components;

import com.github.javafaker.Faker;
import com.sda.company.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomFakerEmployee {

    public List<Employee> createDummyEmployeeList() {
        Faker faker = new Faker();
        List<Employee> employeeList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Employee employee = new Employee();
            employee.setFirstName(faker.name().firstName());
            employee.setLastName(faker.name().lastName());
            employee.setPersonalNumericCode(String.valueOf(faker.number().randomNumber(11, true)));
            employee.setAddress(faker.address().fullAddress());
            employee.setPhone(faker.phoneNumber().phoneNumber());
            employee.setEmail(faker.bothify("?????##@gmail.com"));
            employee.setHired(false);

            employeeList.add(employee);
        }

        return employeeList;
    }
}
