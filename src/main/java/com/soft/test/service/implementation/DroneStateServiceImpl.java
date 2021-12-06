package com.soft.test.service.implementation;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.soft.test.model.drone.DroneState;
import com.soft.test.repository.DroneStateRepo;
import com.soft.test.service.DroneStateService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DroneStateServiceImpl implements DroneStateService {

    private final DroneStateRepo dsRepo;

    @Override
    public DroneState saveDroneState(DroneState droneState) {
        return dsRepo.save(droneState);
    }

    @Override
    public List<DroneState> fetchAllDroneState() {
        return dsRepo.findAll();
    }

    @Override
    public DroneState fetchDroneState(String name) {
        return dsRepo.findByName(name);
    }

    @Override
    public DroneState updateDroneState(UUID id, DroneState droneState) {
        DroneState droneStateToUpdate = dsRepo.getById(id);
        droneStateToUpdate.setName(droneState.getName());
        dsRepo.save(droneStateToUpdate);
        return droneStateToUpdate;
    }

    @Override
    public DroneState deleteDroneState(String name) {
        DroneState droneState = dsRepo.findByName(name);
        dsRepo.delete(droneState);
        return droneState;
    }
    
}
