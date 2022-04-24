package com.example.project.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.Appointment;
import com.example.project.service.AppointmentService;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value="/appointment/register", method = RequestMethod.POST)
    public ResponseEntity<Appointment> registerAppointment(@RequestBody Appointment appointment) {
        Appointment appointment1 = appointmentService.registerAppointment(appointment);
        return ResponseEntity.ok(appointment1);

    }

    @RequestMapping(value="/appointment/list", method = RequestMethod.GET)
    public ResponseEntity<List<Appointment>> getAppointmentList() {
           List<Appointment> allApoingments = appointmentService.getAppointmentList();
           return ResponseEntity.ok(allApoingments);
    }

    @RequestMapping(value="/appointment/view/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAppointment(@PathVariable("id") String id) {
        Optional<Appointment> appointment1 = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment1.get());
    }

    @RequestMapping(value="/appointment/list/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Appointment>> appointmentByPatientId(@PathVariable("id") String id) {
        List<Appointment> allApoingments = appointmentService.getAllAppointmentByPatientId(id);
        return ResponseEntity.ok(allApoingments);
    }

    @RequestMapping(value="/appointment/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAppointment(@PathVariable("id") String id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Deleted");
    }


}
