package com.anfinogenov.springbootcrud.Repository;

import com.anfinogenov.springbootcrud.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Optional<List<Employee>> findByName(String name);
}
