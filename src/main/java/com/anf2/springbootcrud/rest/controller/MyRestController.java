package com.anf2.springbootcrud.rest.controller;

import com.anf2.springbootcrud.rest.entity.Employee;
import com.anf2.springbootcrud.rest.service.exception.EmployeeNotFoundException;
import com.anf2.springbootcrud.rest.service.EmployeeService;
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
  public Employee getEmployeeById(@PathVariable int id) {
    Employee employee = employeeService.getEmployeeById(id);

    return employee;
  }

  @GetMapping("/employees/name")
  @ApiOperation(value = "Finds Employee by name",
                notes = "Provide an employee's name to look up specific employee from the DB",
                response = Employee.class)
  public List<Employee> getEmployeeByName(String name) throws EmployeeNotFoundException {
      try {
        return employeeService.getEmployeeByName(name);
      } catch (Exception e) {
        throw new EmployeeNotFoundException("There is no Employee with name " + name);
      }
  }

  @PostMapping("/employees")
  public Employee addNewEmployee(@RequestBody Employee employee) {
    employeeService.saveEmployee(employee);

    return employee;
  }

  @PutMapping("/employees")
  public Employee updateEmployee(@RequestBody Employee employee) throws EmployeeNotFoundException {
    try {
      employeeService.saveEmployee(employee);
    } catch (Exception e) {
      throw new EmployeeNotFoundException("Employee or Id must not be null!!!");
    }

    return employee;
  }

  @DeleteMapping("/employees/{id}")
  public String deleteEmployeeById(@PathVariable int[] id) {
    StringBuilder listDeletedId = new StringBuilder();

    for(int i = 0; i < id.length; i++) {
      employeeService.deleteEmployeeById(id[i]);
      listDeletedId.append(id[i]).append(", ");
    }


    return "Employee with ID = " + listDeletedId + " was deleted";
  }
  @DeleteMapping("/employees")
  public String deleteEmployeeByName(@RequestParam(value = "name") String name) {
    employeeService.deleteEmployeeByName(name);

    return "Employee with ID = " + name + " was deleted";
  }

}
