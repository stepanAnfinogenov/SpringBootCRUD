package com.anf2.springbootcrud.soap.service.impl;

import com.anf2.springbootcrud.soap.repository.EmployeeRepository;
import com.anf2.springbootcrud.soap.service.EmployeeService;
import com.anf2.springbootcrud.soap.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Employee getEmployeeById(long employeeId) {

        Employee obj = employeeRepository.findEmployeeById(employeeId);
        return obj;

    }

//    @Override
//    public void AddEmployee(Employee employee) {
//        employeeRepository.save(employee);
//    }
//
//    @Override
//    public void updateEmployee(Employee employee) {
//        employeeRepository.save(employee);
//
//    }
//
//    @Override
//    public void deleteEmployee(long employeeId) {
//        employeeRepository.deleteById(employeeId);
//
//    }

}
