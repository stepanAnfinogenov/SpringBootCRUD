package com.anf2.springbootcrud.rest.repository;

import com.anf2.springbootcrud.rest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {
    @Modifying
    @Query("delete from Employee where name=:emplName")
    void deleteEmployeesByName(@Param("emplName") String emplName);
}
