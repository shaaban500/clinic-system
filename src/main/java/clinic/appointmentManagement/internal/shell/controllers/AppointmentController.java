package clinic.appointmentManagement.internal.shell.controllers;//package com.appointmentManagement.shell.controllers;


import clinic.appointmentManagement.internal.core.services.AppointmentService;
import clinic.appointmentManagement.internal.core.services.dtos.AppointmentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<AppointmentDto>> getUpComingAppointments() {
        List<AppointmentDto> appointments = appointmentService.getUpComingAppointments();
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<Void> updateStatus(
            @PathVariable UUID appointmentId,
            @RequestParam(name = "status_id", required = true) Integer statusId) {
        appointmentService.UpdateStatus(appointmentId, statusId);
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