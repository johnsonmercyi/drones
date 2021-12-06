package com.soft.test.service.implementation;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.soft.test.model.medication.Medication;
import com.soft.test.repository.MedicationRepo;
import com.soft.test.service.MedicationService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicationServiceImpl implements MedicationService {

    private final MedicationRepo medRepo;

    @Override
    public Medication registerMedication(Medication medication) {
        return medRepo.save(medication);
    }

    @Override
    public List<Medication> fetchAllMedications() {
        return medRepo.findAll();
    }

    @Override
    public Medication fetchMedication(String name) {
        return medRepo.findByName(name);
    }

    @Override
    public Medication updateMedication(UUID id, Medication medication) {
        Medication medToUpdate = medRepo.getById(id);
        medToUpdate.setName(medication.getName());
        medToUpdate.setWeight(medication.getWeight());
        medToUpdate.setCode(medication.getCode());
        medToUpdate.setImage(medication.getImage());
        return medRepo.save(medToUpdate);
    }

    @Override
    public Medication deleteMedication(String name) {
        Medication med = medRepo.findByName(name);
        medRepo.delete(med);
        return med;
    }

}
