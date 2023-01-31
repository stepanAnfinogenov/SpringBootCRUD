package com.anf2.springbootcrud.soap.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "department")
    private String department;

    @Column(name = "salary")
    private int salary;
}
