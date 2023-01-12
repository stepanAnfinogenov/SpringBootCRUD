package com.anfinogenov.springbootcrud.service;

import com.anfinogenov.springbootcrud.entity.Employee;

import java.util.List;

public interface EmployeeService {
  public List<Employee> getAllEmployees();
  public Employee saveEmployee(Employee employee);
  public Employee getEmployee(int id);
  public void deleteEmployee(int id);
  List<Employee> getEmployeeByName(String name);
}
