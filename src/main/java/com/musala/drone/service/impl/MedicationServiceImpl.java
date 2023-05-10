package com.musala.drone.service.impl;

import com.musala.drone.entity.Medication;
import com.musala.drone.repository.MedicationRepository;
import com.musala.drone.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService {
    @Autowired
    MedicationRepository medicationRepository;
    @Override
    public List<Medication> getAll() {
        return medicationRepository.findAll();
    }
}
