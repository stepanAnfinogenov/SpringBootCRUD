package com.anf2.springbootcrud.rest.repository;

import com.anf2.springbootcrud.rest.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceJpaRepository extends JpaRepository<Device, Long> {
}