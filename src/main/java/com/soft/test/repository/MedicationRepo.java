package com.soft.test.repository;

import java.util.UUID;

import com.soft.test.model.medication.Medication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepo extends JpaRepository<Medication, UUID> {
    Medication findByName (String name);
}
