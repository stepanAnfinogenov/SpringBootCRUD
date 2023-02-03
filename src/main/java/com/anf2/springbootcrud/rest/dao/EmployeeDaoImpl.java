package com.anf2.springbootcrud.rest.dao;

import com.anf2.springbootcrud.rest.entity.Employee;

//import org.hibernate.Session;
//import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {
//        Session session = entityManager.unwrap(Session.class);
//
//        Query<Employee> query = session.createQuery("from Employee", Employee.class);
//        List<Employee> allEmployees = query.getResultList();

        Query query = entityManager.createQuery("from Employee");
        List<Employee> allEmployees = query.getResultList();

        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
//        Session session = entityManager.unwrap(Session.class);
//        session.saveOrUpdate(employee);

        Employee newEmployee = entityManager.merge(employee);
        employee.setEmployeeId(newEmployee.getEmployeeId());
    }

    @Override
    public Employee getEmployees(long employeeId) {
//        Session session = entityManager.unwrap(Session.class);
//        Employee employee = session.get(Employee.class, id);

        Employee employee = entityManager.find(Employee.class, employeeId);

        return employee;
    }

    @Override
    public void deleteEmployeeById(long employeeId) {
//        Session session = entityManager.unwrap(Session.class);
//        Query<Employee> query = session.createQuery("delete from Employee where id =:employeeId");
//        query.setParameter("employeeId", id);
//        query.executeUpdate();

        Query query = entityManager.createQuery("delete from Employee where employeeId =:employeeId");
        query.setParameter("employeeId", employeeId);
        query.executeUpdate();
    }

    @Override
    public void deleteEmployeeByName(String nameEmpl) {

        Query query = entityManager.createQuery("delete from Employee where name =:employeeName");
        query.setParameter("employeeName", nameEmpl);
        query.executeUpdate();
    }
}
