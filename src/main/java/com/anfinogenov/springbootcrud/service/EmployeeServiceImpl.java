package com.anfinogenov.springbootcrud.service;

import com.anfinogenov.springbootcrud.repository.EmployeeCrudRepository;
import com.anfinogenov.springbootcrud.repository.EmployeeJpaRepository;
import com.anfinogenov.springbootcrud.dao.EmployeeDao;
import com.anfinogenov.springbootcrud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeCrudRepository employeeCrudRepository;

    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee) {
        employeeDao.saveEmployee(employee);

        return employee;
    }

    @Override
    @Transactional
    public Employee getEmployeeById(int id) {
        return employeeDao.getEmployees(id);
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        return employeeCrudRepository.findByName(name).get();
    }

    @Override
    @Transactional
    public void deleteEmployeeById(int id) {
        employeeDao.deleteEmployeeById(id);
    }

    @Override
    public void deleteEmployeeByName(String name) {
        employeeJpaRepository.deleteUsersByName(name);
//        employeeCrudRepository.deleteByName(name);
    }
}
