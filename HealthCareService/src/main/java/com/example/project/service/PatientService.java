package com.example.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Model.Patient;
import com.example.project.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    Logger logger = LoggerFactory.getLogger(PatientService.class);
    @Autowired
    private PatientRepository patientRepository;


    public Patient registerPatient(Patient patient) {
        Patient patient1 = patientRepository.save(patient);
        logger.info(patient1.getPatient_Id());
        return patient1;
    }

    public List<Patient> returnAllPatients() {
        logger.info("inside return all patients");
        List<Patient> patientList = patientRepository.findAll();
        logger.info(patientList.toString()
        );
        return patientList;
    }

    public Optional<Patient> viewProfileById(String id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient;
    }

    public void deleteProfileById(String id){
        patientRepository.deleteById(id);
    }
}
