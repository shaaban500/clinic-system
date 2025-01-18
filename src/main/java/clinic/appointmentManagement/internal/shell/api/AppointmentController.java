package clinic.appointmentManagement.internal.shell.api;//package com.appointmentManagement.shell.controllers;


import clinic.appointmentManagement.internal.core.services.AppointmentHandler;
import clinic.appointmentManagement.internal.core.services.dtos.AppointmentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/appointments")
public class AppointmentController {
    private final AppointmentHandler appointmentHandler;

    public AppointmentController(AppointmentHandler appointmentHandler) {
        this.appointmentHandler = appointmentHandler;
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<AppointmentDto>> getUpComingAppointments() {
        List<AppointmentDto> appointments = appointmentHandler.getUpComingAppointments();
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<Void> updateStatus(
            @PathVariable UUID appointmentId,
            @RequestParam(name = "status_id", required = true) Integer statusId) {
        appointmentHandler.UpdateStatus(appointmentId, statusId);
        return ResponseEntity.ok().build();
    }

//    @PutMapping("/{appointmentId}/status")
//    public ResponseEntity<Void> updateStatus(
//            @PathVariable UUID appointmentId,
//            @RequestParam(name = "status_id", required = true) Integer statusId) { // Not null query parameter
//        appointmentService.UpdateStatus(appointmentId, statusId);
//        return ResponseEntity.ok().build();
//    }

//    @PutMapping("/{appointmentId}/{statusId}")
//    public ResponseEntity<Void> updateStatus(
//            @PathVariable UUID appointmentId,
//            @PathVariable Integer statusId){
//            @RequestParam(name = "status_id", required = true) Integer statusId) {
//        appointmentService.UpdateStatus(appointmentId, statusId);
//        return ResponseEntity.ok().build();
//    }
}