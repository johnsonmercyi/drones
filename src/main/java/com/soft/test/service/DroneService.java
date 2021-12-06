package com.soft.test.service;

import java.util.List;
import java.util.UUID;

import com.soft.test.model.drone.Drone;
import com.soft.test.model.medication.Medication;

public interface DroneService {
    Drone registerDrone (Drone drone);
    List<Drone> fetchAllDrones();
    Drone fetchDrone(String serialNo);
    Drone loadDrone (UUID id, String medicationName) throws RuntimeException;
    List <Medication> loadedMedicationItems(String serialNo);
    List <Drone> availableDrones ();
    String getDroneBatteryLevel (String serialNo);
    Drone updateDroneBatteryCapacity (UUID id, int batteryCapacity);
}
