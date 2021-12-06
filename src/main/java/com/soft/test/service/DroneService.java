package com.soft.test.service;

import java.util.List;

import com.soft.test.model.drone.Drone;
import com.soft.test.model.medication.Medication;

public interface DroneService {
    Drone registerDrone (Drone drone);
    List<Drone> fetchAllDrones();
    Drone fetchDrone(String serialNo);
    Drone loadDrone (String serialNo, Medication medication);
    List <Medication> loadedMedicationItems(String serialNo);
    List <Drone> availableDrones ();
    String getDroneBatteryLevel (String serialNo);
}
