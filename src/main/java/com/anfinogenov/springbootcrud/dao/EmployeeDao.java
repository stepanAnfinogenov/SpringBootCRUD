package com.anfinogenov.springbootcrud.dao;

import com.anfinogenov.springbootcrud.entity.Employee;

import java.util.List;


public interface EmployeeDao {
    public List<Employee> getAllEmployees();

//    public void saveEmployee(Employee employee);
//
//    public Employee getEmployees(int id);
//
//    public void deleteEmployee(int id);
}
