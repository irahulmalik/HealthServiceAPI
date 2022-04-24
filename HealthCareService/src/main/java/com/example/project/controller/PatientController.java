package com.example.project.controller;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.Patient;
import com.example.project.service.PatientService;
@RestController
public class PatientController {

    Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @RequestMapping(value="/patients/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody Patient patient){
        patientService.registerPatient(patient);
        return ResponseEntity.ok("Successfull");
    }

    @RequestMapping(value="/patients/list", method = RequestMethod.GET)
    public ResponseEntity<List<Patient>> listPatients(){
//return all patients list
        logger.info("list all patiensts");
        List<Patient> allPatients = patientService.returnAllPatients();
        logger.info(allPatients.toString());
        return ResponseEntity.ok(allPatients);

    }

    @RequestMapping(value="/patients/view/{id}", method = RequestMethod.GET)
    public ResponseEntity<Patient> viewProfile(@PathVariable("id") String id) {
        Optional<Patient> patient = patientService.viewProfileById(id);
        return ResponseEntity.ok(patient.get());
    }

    @RequestMapping(value="/patients/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProfile(@PathVariable("id") String id){
        patientService.deleteProfileById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Delete Successful");
    }

    }
