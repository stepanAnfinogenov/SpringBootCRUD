package com.anf2.springbootcrud.rest.service;

import com.anf2.springbootcrud.rest.entity.Employee;

import java.util.List;

public interface EmployeeService {
  public List<Employee> getAllEmployees();
  public Employee saveEmployee(Employee employee);
  public Employee getEmployeeById(long id);
  List<Employee> getEmployeeByName(String name);
  public void deleteEmployeeById(long id);

  void deleteEmployeeByName(String name);

  void save(Employee employee);
}
