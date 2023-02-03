package com.anf2.springbootcrud.rest.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
@Builder
@ApiModel(description = "Details about the Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    @ApiModelProperty(notes = "The unique id of the Employee")
    private  Long employeeId;

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
}
