package com.soft.test.config;

import java.time.LocalDateTime;
import java.util.List;

import com.soft.test.config.enums.DroneState;
import com.soft.test.model.drone.Drone;
import com.soft.test.model.droneBatteryCheckLog.DroneBatteryCheckLog;
import com.soft.test.service.DroneBatteryCheckLogService;
import com.soft.test.service.DroneService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DroneTaskScheduler {

    private final DroneService droneService;
    private final DroneBatteryCheckLogService batteryCheckService;

    @Scheduled(cron = "${scheduled-task-interval}")
    public void checkDroneBatteryLevel() {
        List<Drone> drones = droneService.fetchAllDrones();
        if (drones.size() > 0) {
            drones.stream().forEach(drone -> {
                if (drone.getState().equals(DroneState.IDLE.name())) {
                    this.logBatterCheck(drone);
                } else {
                    Drone updatedDrone = droneService.updateDroneBatteryCapacity(drone.getId(),drone.getBatteryCapacity() - 1);
                    this.logBatterCheck(updatedDrone);
                }
            });
            log.info("Drone battery checked & logged @ {} ", LocalDateTime.now());
        } else {
            log.info("Attempted Drone battery check - No Drone found: {}", LocalDateTime.now());
        }
        
    }

    private void logBatterCheck(Drone drone) {
        DroneBatteryCheckLog log = new DroneBatteryCheckLog();
        log.setDrone(drone);
        log.setBatteryCapacity(drone.getBatteryCapacity());
        batteryCheckService.log(log);
    }
}
