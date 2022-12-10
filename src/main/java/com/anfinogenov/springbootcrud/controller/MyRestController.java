package com.anfinogenov.springbootcrud.controller;

import com.anfinogenov.springbootcrud.entity.Employee;
import com.anfinogenov.springbootcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//  @RequestMapping("/addNewEmployee")
//  public String addNewEmployee(Model model) {
//    Employee employee = new Employee();
//    model.addAttribute("employee", employee);
//
//    return "employee-info";
//  }

//  @RequestMapping("/saveEmployee")
//  public String saveEmployee(@ModelAttribute("employee") Employee employee ) {
//
//    employeeService.saveEmployee(employee);
//
//    return "redirect:/";
//  }
//
//  @RequestMapping("/updateInfo")
//  public String updateEmployee(@RequestParam("empId") int id, Model model) {
//    Employee employee = employeeService.getEmployee(id);
//    model.addAttribute("employee", employee);
//
//    return "employee-info";
//  }
//
//  @RequestMapping("/deleteEmployee")
//  public String deleteEmployee(@RequestParam("empId") int id) {
//    employeeService.deleteEmployee(id);
//
//    return "redirect:/";
//  }

}
