package com.anf2.springbootcrud.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id", updatable = false)
    private Long deviceId;

    //    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "employee_id")
//    private Employee employee;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empl_id")
    @JsonIgnore
    private Employee employee;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private String price;


}
