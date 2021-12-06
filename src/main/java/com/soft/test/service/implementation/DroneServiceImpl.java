package com.soft.test.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.soft.test.config.enums.DroneState;
import com.soft.test.model.drone.Drone;
import com.soft.test.model.medication.Medication;
import com.soft.test.repository.DroneRepo;
import com.soft.test.repository.MedicationRepo;
import com.soft.test.service.DroneService;
import com.soft.test.service.MedicationService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DroneServiceImpl implements DroneService {

    private final DroneRepo droneRepo;
    private final MedicationRepo medRepo;
    private int medItemsTotalWeight = 0;

    @Override
    public Drone registerDrone(Drone drone) {
        return droneRepo.save(drone);
    }

    @Override
    public List<Drone> fetchAllDrones() {
        return droneRepo.findAll();
    }

    @Override
    public Drone fetchDrone(String serialNo) {
        return droneRepo.findBySerialNo(serialNo);
    }

    @Override
    public Drone loadDrone(UUID id, String medicationName) throws RuntimeException {
        Drone drone = droneRepo.getById(id);
        int weightLimit = drone.getWeightLimit();
        Medication medToLoad = medRepo.findByName(medicationName);
        log.info("Medication to be loaded: {}", medToLoad);

        if (medToLoad == null) {
            throw new RuntimeException("Medication doesn't exist!");
        }

        if (drone.getBatteryCapacity() > 25) {

            if (drone.getState().equals(DroneState.LOADING.name())) {
                drone.getMedications().forEach(med -> {
                    medItemsTotalWeight += med.getWeight();
                });

                log.info("Drone Medication Total Weight: {} and weight limit is: {}", medItemsTotalWeight, weightLimit);
            }
    
            if (medItemsTotalWeight < weightLimit) {
                int futureWeight = medItemsTotalWeight + medToLoad.getWeight();
    
                if (futureWeight > weightLimit) {
                    throw new RuntimeException("Drone Cannot be Overloaded!");
                } else {
                    drone.getMedications().add(medToLoad);
                    drone.setState(DroneState.LOADING.name());
                    droneRepo.save(drone);
                }
            }

        } else {
            throw new RuntimeException("Drone Not Charged!");
        }
        
        return drone;
    }

    @Override
    public List<Medication> loadedMedicationItems(String serialNo) {
        Drone drone = droneRepo.findBySerialNo(serialNo);
        return (List<Medication>) drone.getMedications();
    }

    @Override
    public List<Drone> availableDrones() {
        List <Drone> allDrones = droneRepo.findAll();
        List <Drone> availableDrones = new ArrayList<>();

        allDrones.stream().forEach(drone -> {
            if (drone.getState().equals(DroneState.IDLE.name())) {
                availableDrones.add(drone);
            }
        });
        return availableDrones;
    }

    @Override
    public String getDroneBatteryLevel(String serialNo) {
        Drone drone = droneRepo.findBySerialNo(serialNo);
        return String.format("%d%s", drone.getBatteryCapacity(), "%");
    }

    @Override
    public Drone updateDroneBatteryCapacity (UUID id, int batteryCapacity) {
        Drone droneToUpdate = droneRepo.getById(id);
        droneToUpdate.setBatteryCapacity(batteryCapacity);
        return droneToUpdate;
    }
    
}
