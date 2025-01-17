package clinic.appointmentManagement.core.models;//package com.appointmentManagement.core.models;

import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {
    private UUID id;
    private UUID slotId;
    private UUID patientId;
    private LocalDateTime reservedAt;
}
