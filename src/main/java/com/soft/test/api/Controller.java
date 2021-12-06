package com.soft.test.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soft.test.model.drone.Drone;
import com.soft.test.model.medication.Medication;
import com.soft.test.service.DroneService;
import com.soft.test.service.MedicationService;
import com.soft.test.utility.Util;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class Controller {
    private final DroneService droneService;
    private final MedicationService medService;

    @GetMapping("/drones")
    public ResponseEntity<List<Drone>> fetchAllDrones() {
        return ResponseEntity.ok().body(droneService.fetchAllDrones());
    }

    @PostMapping("/drones/save")
    public ResponseEntity<Drone> registerDrone(@RequestBody Drone drone, HttpServletResponse response) {
        // DataIntegrityViolationException
        Map<String, Object> error = new HashMap<>();
        Drone registeredDrone = null;

        try {
            registeredDrone = droneService.registerDrone(drone);
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                error.put("error", Boolean.TRUE);
                error.put("message", "Drone already exist!");
            } else {
                error.put("error", Boolean.TRUE);
                error.put("message", e.getMessage());
            }
        }

        if (error.get("error") == Boolean.TRUE) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            try {
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return ResponseEntity.internalServerError().build();
        } else {
            return ResponseEntity.created(Util.getPathUri("/api/v1/drones/save")).body(registeredDrone);
        }

        
    }

    @GetMapping("/drones/available")
    public ResponseEntity<List<Drone>> getAvailableDrones() {
        return ResponseEntity.ok().body(droneService.availableDrones());
    }

    @GetMapping("/drones/{serialNo}")
    public ResponseEntity<Drone> fetchDrone(@PathVariable String serialNo) {
        return ResponseEntity.ok().body(droneService.fetchDrone(serialNo));
    }

    @GetMapping("/drones/loadedItems/{serialNo}")
    public ResponseEntity<List<Medication>> getLoadedItems(@PathVariable String serialNo, HttpServletResponse response) {
        Map<String, Object> error = new HashMap<>();
        List<Medication> meds = null;
        try {
            meds = droneService.loadedMedicationItems(serialNo);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                error.put("error", Boolean.TRUE);
                error.put("message", "Drone not found!");
            } else {
                error.put("error", Boolean.TRUE);
                error.put("message", e.getMessage());
            }

        }

        if (error.get("error") == Boolean.TRUE) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            try {
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return ResponseEntity.internalServerError().build();
        } else {
            return ResponseEntity.ok().body(meds);
        }

    }

    @GetMapping("/drones/battery_level/{serialNo}")
    public ResponseEntity<String> checkDroneBatteryLevel(@PathVariable String serialNo, HttpServletResponse response) {
        Map<String, Object> outcome = new HashMap<>();
        String batLevel = droneService.getDroneBatteryLevel(serialNo);
        outcome.put("Drone Serial No", serialNo);
        outcome.put("Battery Level", batLevel);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        try {
            new ObjectMapper().writeValue(response.getOutputStream(), outcome);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(batLevel);
    }

    @PostMapping("/drones/load")
    public ResponseEntity<?> loadDrone(@RequestBody DroneLoadForm form, HttpServletResponse response) {
        Map<String, Object> outcome = new HashMap<>();

        try {
            Drone drone = droneService.loadDrone(form.getId(), form.getMedicationName());
            if (drone != null) {
                outcome.put("success", "Medication " + form.getMedicationName() + " loaded successfully");
            }
        } catch (Exception e) {
            outcome.put("error", Boolean.TRUE);
            outcome.put("message", e.getMessage());
        }

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        try {
            new ObjectMapper().writeValue(response.getOutputStream(), outcome);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/medications")
    public ResponseEntity<List<Medication>> fetchMedications() {
        return ResponseEntity.ok().body(medService.fetchAllMedications());
    }

}

@Data
class DroneLoadedItemsForm {
    private String serialNo;
}

@Data
class DroneLoadForm {
    private UUID id;
    private String medicationName;
}
