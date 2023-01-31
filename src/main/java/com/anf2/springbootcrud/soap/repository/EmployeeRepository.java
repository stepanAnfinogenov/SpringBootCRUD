package com.anf2.springbootcrud.soap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.anf2.springbootcrud.soap.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeById(long id);
}
