package com.soft.test.repository;

import java.util.UUID;

import com.soft.test.model.drone.DroneState;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneStateRepo extends JpaRepository<DroneState, UUID> {
    DroneState findByName(String name);
}
