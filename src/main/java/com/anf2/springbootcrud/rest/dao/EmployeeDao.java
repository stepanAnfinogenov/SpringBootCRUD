package com.anf2.springbootcrud.rest.dao;

import com.anf2.springbootcrud.rest.entity.Employee;

import java.util.List;


public interface EmployeeDao {
    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public Employee getEmployees(int id);

    public void deleteEmployeeById(int id);

    void deleteEmployeeByName(String name);
}
