package com.soft.test.service.implementation;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.soft.test.model.droneBatteryCheckLog.DroneBatteryCheckLog;
import com.soft.test.repository.DroneBatteryCheckLogRepo;
import com.soft.test.service.DroneBatteryCheckLogService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DroneBatteryCheckLogServiceImpl implements DroneBatteryCheckLogService {

    private final DroneBatteryCheckLogRepo repo;

    @Override
    public DroneBatteryCheckLog log(DroneBatteryCheckLog droneBatteryCheckLog) {
        return repo.save(droneBatteryCheckLog);
    }

    @Override
    public List<DroneBatteryCheckLog> fetchAllLogs() {
        return repo.findAll();
    }

    @Override
    public List<DroneBatteryCheckLog> fetchLogsByDroneId(UUID droneId) {
        return repo.findByDroneId(droneId);
    }
    
}
