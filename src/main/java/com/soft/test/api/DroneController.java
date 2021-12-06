package com.soft.test.api;

import java.util.List;
import java.util.UUID;

import com.soft.test.model.drone.Drone;
import com.soft.test.model.medication.Medication;
import com.soft.test.service.DroneService;
import com.soft.test.utility.Util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DroneController {
    private final DroneService droneService;

    @GetMapping("/drones")
    public ResponseEntity<List<Drone>> fetchAllDrones () {
        return ResponseEntity.ok().body(droneService.fetchAllDrones());
    }

    @GetMapping("/drones/{serialNo}")
    public ResponseEntity<Drone> fetchDrone (@PathVariable String serialNo) {
        return ResponseEntity.ok().body(droneService.fetchDrone(serialNo));
    }
    
    @PostMapping("/drones/save")
    public ResponseEntity<Drone> registerDrone (@RequestBody Drone drone) {
        return ResponseEntity.created(Util.getPathUri("/api/v1/drones/save")).body(droneService.registerDrone(drone));
    }

    @GetMapping("/drones/available")
    public ResponseEntity<List<Drone>> getAvailableDrones () {
        return ResponseEntity.ok().body(droneService.availableDrones());
    }

    @PostMapping("/drones/load/{id}")
    public ResponseEntity<Drone> loadDrone (@PathVariable UUID id, @RequestBody Medication medication) {
        return ResponseEntity.ok().body(droneService.loadDrone(id, medication));
    }

    @GetMapping("/drones/{serialNo}/loadedItems")
    public ResponseEntity<List<Medication>> getLoadedItems (@PathVariable String serialNo) {
        return ResponseEntity.ok().body(droneService.loadedMedicationItems(serialNo));
    }

    @GetMapping("/drone/{serialNo}/battery_level")
    public ResponseEntity<String> checkDroneBatteryLevel (@PathVariable String serialNo) {
        return ResponseEntity.ok().body(droneService.getDroneBatteryLevel(serialNo));
    }

    
}
