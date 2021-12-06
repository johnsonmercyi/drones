package com.soft.test.repository;

import java.util.UUID;

import com.soft.test.model.drone.Drone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepo extends JpaRepository<Drone, UUID> {
    Drone findBySerialNo(String serialNo);
}
