package com.anf2.springbootcrud.soap.repository;

import io.spring.guides.gs_producing_web_service.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Component
public class EmployeeRepository {
    private static final Map<String, Employee> employees = new HashMap<>();

    @PostConstruct
    public void initData() {
        Employee employee1 = new Employee();
        employee1.setId(new BigInteger(String.valueOf(1)));
        employee1.setName("Name1");
        employee1.setSurname("Surname1");
        employee1.setDepartment("Department1");
        employee1.setSalary(1000);

        employees.put(employee1.getName(), employee1);

        Employee employee2 = new Employee();
        employee1.setId(new BigInteger(String.valueOf(2)));
        employee2.setName("Name2");
        employee2.setSurname("Surname2");
        employee2.setDepartment("Department2");
        employee2.setSalary(2000);

        employees.put(employee2.getName(), employee2);

    }

    public Employee findEmployee(String name) {
        Assert.notNull(name, "The employee's name must not be null");
        return employees.get(name);
    }
}