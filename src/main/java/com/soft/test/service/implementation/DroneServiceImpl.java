package com.soft.test.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.soft.test.config.enums.DroneState;
import com.soft.test.model.drone.Drone;
import com.soft.test.model.medication.Medication;
import com.soft.test.repository.DroneRepo;
import com.soft.test.service.DroneService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {

    private final DroneRepo droneRepo;
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
    public Drone loadDrone(String serialNo, Medication medicationItem) {
        Drone drone = droneRepo.findBySerialNo(serialNo);
        int weight = drone.getWeightLimit();
        

        if (drone.getState().getName().equals(DroneState.LOADING.name())) {
            drone.getMedications().forEach(med -> {
                medItemsTotalWeight += med.getWeight();
            });
        }

        if (medItemsTotalWeight < weight) {
            int futureWeight = medItemsTotalWeight + weight;

            if (futureWeight > weight) {
                throw new RuntimeException("Drone Overload!");
            } else {
                drone.getMedications().add(medicationItem);

                // drone.setState();
            }
        }
        
        return drone;
    }

    @Override
    public List<Medication> loadedMedicationItems(String serialNo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Drone> availableDrones() {
        List <Drone> allDrones = droneRepo.findAll();
        List <Drone> availableDrones = new ArrayList<>();

        allDrones.stream().forEach(drone -> {
            if (drone.getState().getName().equals(DroneState.IDLE.name())) {
                availableDrones.add(drone);
            }
        });
        return availableDrones;
    }

    @Override
    public String getDroneBatteryLevel(String serialNo) {
        Drone drone = droneRepo.findBySerialNo(serialNo);
        return String.format("%s%", drone.getBatteryCapacity());
    }
    
}
