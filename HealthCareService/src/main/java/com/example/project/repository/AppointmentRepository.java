package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.Model.Appointment;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,String>{
    /**
     * @param patientId
     * @return List of all Appointment made by patient with id = patientId
     */
    List<Appointment> findAllBypatientId(String patientId);
}
