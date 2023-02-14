package com.anf2.springbootcrud.rest.controller;

import com.anf2.springbootcrud.rest.entity.Employee;
import com.anf2.springbootcrud.rest.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ControllerView {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerView.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public String showAllEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("allEmployee", employees);

        return "employee-list";
    }

    @GetMapping("/createNewEmployee")
    public String createEmployeePage (Model model) {
        model.addAttribute(employeeService.getAllEmployees());
        model.addAttribute("newemployee", new Employee());

        return "employee-create";
    }

    @PostMapping("/addEmployee")
    public String createEmployee(Employee employee) {
        employeeService.saveEmployee(employee);

        return "redirect:/employees";
    }

    @GetMapping("employee-delete/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") Long employeeId){
        employeeService.deleteEmployeeById(employeeId);
        return "redirect:/employees";
    }

    @GetMapping("/employee-update/{id}")
    public String updateEmployeeForm(@PathVariable("id") Long id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee-update";
    }

    @PostMapping("/employee-update")
    public String updateEmployee(Employee employee){
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @RequestMapping("/2")
    public String defaultTestPage2 () {
        return "test-page2";
    }
}

