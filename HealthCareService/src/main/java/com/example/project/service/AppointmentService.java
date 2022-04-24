package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.project.Model.Appointment;


@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    public Appointment registerAppointment(Appointment appointment) {
        Appointment appointment1 = repository.save(appointment);
        return appointment1;
    }

    public List<Appointment> getAppointmentList() {
        return repository.findAll();
    }

    public Optional<Appointment> getAppointmentById(String id) {
        Optional<Appointment> appointment = repository.findById(id.toString());
        return appointment;
    }

    public List<Appointment> getAllAppointmentByPatientId(String patientId) {
//    return all appointmaints made by a user
        return repository.findAllBypatientId(patientId);
    }

    public void deleteAppointment(String id) {
        repository.deleteById(id.toString());
        return;
    }
	
}
