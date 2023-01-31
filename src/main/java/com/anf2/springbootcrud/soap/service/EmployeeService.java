package com.anf2.springbootcrud.soap.service;

import com.anf2.springbootcrud.soap.entity.Employee;

public interface EmployeeService {
    Employee getEmployeeById(long employeeId);
}
