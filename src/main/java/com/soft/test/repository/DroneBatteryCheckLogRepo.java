package com.soft.test.repository;

import java.util.List;
import java.util.UUID;

import com.soft.test.model.droneBatteryCheckLog.DroneBatteryCheckLog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneBatteryCheckLogRepo extends JpaRepository<DroneBatteryCheckLog, UUID> {
    List <DroneBatteryCheckLog> findByDroneId (UUID droneId);
}
