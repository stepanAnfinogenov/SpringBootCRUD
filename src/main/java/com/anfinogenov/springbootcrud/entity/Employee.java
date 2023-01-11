package com.anfinogenov.springbootcrud.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@ApiModel(description = "Details about the Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "The unique id of the Employee")
    private  int id;


    @Column(name = "name")
    @ApiModelProperty(notes = "The Employee's name")
    private String name;
    @Column(name = "surname")
    @ApiModelProperty(notes = "The Employee's surname")
    private String surname;

    @Column(name = "department")
    @ApiModelProperty(notes = "The Employee's department")
    private String department;

    @Column(name = "salary")
    @ApiModelProperty(notes = "The Employee's salary")
    private int salary;

    public Employee() {
    }

    public Employee(String name, String surname, String department, int salary) {
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
