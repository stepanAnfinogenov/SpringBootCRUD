package com.anf2.springbootcrud.rest.controller;

import com.anf2.springbootcrud.rest.entity.Employee;
import com.anf2.springbootcrud.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class ControllerHtml {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/createNewEmployee")
    public String createEmployeePage (Model model) {
        model.addAttribute(employeeService.getAllEmployees());
        model.addAttribute("newemployee", new Employee());

        return "index";
    }

    @PostMapping("/addEmployee")
    public String createEmployee(Employee employee) {
        employeeService.saveEmployee(employee);

        return "redirect:/create";
    }

    @RequestMapping("/2")
    public String defaultPage2 () {
        return "<!DOCTYPE html><html><head>TEXT1!!!!!!!!!!!!<br><br><br><br>" +
                "</head><body>TEXT2%%%%%%%%%%%%%%<br><br><br><br>" +
                "TEXT3&&&&&&&&&&&</body></html>";
    }
}
