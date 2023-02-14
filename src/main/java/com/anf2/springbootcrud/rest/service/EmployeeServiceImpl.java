package com.anf2.springbootcrud.rest.service;

import com.anf2.springbootcrud.rest.repository.EmployeeCrudRepository;
import com.anf2.springbootcrud.rest.repository.EmployeeJpaRepository;
import com.anf2.springbootcrud.rest.dao.EmployeeDao;
import com.anf2.springbootcrud.rest.entity.Employee;
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
    public Employee getEmployeeById(long id) {
        return employeeDao.getEmployees(id);
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        return employeeCrudRepository.findByName(name).get();
    }

    @Override
    @Transactional
    public void deleteEmployeeById(long id) {
        employeeDao.deleteEmployeeById(id);
    }

    @Override
    public void deleteEmployeeByName(String name) {
        employeeJpaRepository.deleteEmployeesByName(name);
//        employeeCrudRepository.deleteByName(name);
    }

    @Override
    public void save(Employee employee) {
        employeeCrudRepository.save(employee);
    }
}
