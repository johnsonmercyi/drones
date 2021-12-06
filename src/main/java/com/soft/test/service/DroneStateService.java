package com.soft.test.service;

import java.util.List;
import java.util.UUID;

import com.soft.test.model.drone.DroneState;

public interface DroneStateService {
    DroneState saveDroneState(DroneState droneState);
    List<DroneState> fetchAllDroneState ();
    DroneState fetchDroneState (String name);
    DroneState updateDroneState (UUID id, DroneState droneState);
    DroneState deleteDroneState (String name);
}
