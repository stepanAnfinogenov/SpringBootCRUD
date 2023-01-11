package com.anfinogenov.springbootcrud.controller;

import com.anfinogenov.springbootcrud.entity.Employee;
import com.anfinogenov.springbootcrud.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping("/employees")
  public List<Employee> showAllEmployees(Model model) {
    List<Employee> allEmployees = employeeService.getAllEmployees();

    return allEmployees;
  }

  @GetMapping("/employees/{id}")
  @ApiOperation(value = "Finds Employee by id",
                notes = "Provide an id to look up specific employye from the DB",
                response = Employee.class)
  public Employee getEmployee(@PathVariable int id) {
    Employee employee = employeeService.getEmployee(id);

    return employee;
  }

  @PostMapping("/employees")
  public Employee addNewEmployee(@RequestBody Employee employee) {
    employeeService.saveEmployee(employee);

    return employee;
  }

  @PutMapping("/employees")
  public Employee updateEmployee(@RequestBody Employee employee) {
    employeeService.saveEmployee(employee);

    return employee;
  }

  @DeleteMapping("/employees/{id}")
  public String deleteEmployee(@PathVariable int id) {
    employeeService.deleteEmployee(id);

    return "Employee with ID = " + id + "was deleted";
  }

}
