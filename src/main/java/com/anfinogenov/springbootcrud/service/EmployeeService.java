package com.anfinogenov.springbootcrud.service;

import com.anfinogenov.springbootcrud.entity.Employee;

import java.util.List;

public interface EmployeeService {
  public List<Employee> getAllEmployees();
  public Employee saveEmployee(Employee employee);
  public Employee getEmployeeById(int id);
  List<Employee> getEmployeeByName(String name);
  public void deleteEmployeeById(int id);

  void deleteEmployeeByName(String name);
}
