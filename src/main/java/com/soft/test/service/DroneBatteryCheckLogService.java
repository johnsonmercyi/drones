package com.soft.test.service;

import java.util.List;
import java.util.UUID;

import com.soft.test.model.droneBatteryCheckLog.DroneBatteryCheckLog;

public interface DroneBatteryCheckLogService {
    DroneBatteryCheckLog log(DroneBatteryCheckLog droneBatteryCheckLog);
    List<DroneBatteryCheckLog> fetchAllLogs ();
    List<DroneBatteryCheckLog> fetchLogsByDroneId (UUID droneId);
}
