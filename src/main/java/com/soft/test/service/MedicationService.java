package com.soft.test.service;

import java.util.List;
import java.util.UUID;

import com.soft.test.model.medication.Medication;

public interface MedicationService {
    Medication registerMedication(Medication medication);
    List <Medication> fetchAllMedications ();
    Medication fetchMedication (String name);
    Medication updateMedication (UUID id, Medication medication);
    Medication deleteMedication (String name);
}
