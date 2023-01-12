package com.anfinogenov.springbootcrud.service;

import com.anfinogenov.springbootcrud.Repository.EmployeeRepository;
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
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeDao.saveEmployee(employee);
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        return employeeDao.getEmployees(id);
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        employeeDao.deleteEmployee(id);
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        return employeeRepository.findByName(name).get();
    }
}
