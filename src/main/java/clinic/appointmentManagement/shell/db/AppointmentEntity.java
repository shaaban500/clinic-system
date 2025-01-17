package clinic.appointmentManagement.shell.db;//package com.appointmentManagement.shell.db;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "AppointmentEntity")
@Table(name = "appointments")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "slot_id", nullable = false)
    private UUID slotId;

    @Column(name = "patient_id", nullable = false)
    private UUID patientId;

    @Column(name = "reserved_at", nullable = false)
    private LocalDateTime reservedAt;

    public AppointmentEntity() {
    }

    public AppointmentEntity(UUID id, UUID slotId, UUID patientId, LocalDateTime reservedAt) {
        this.id = id;
        this.slotId = slotId;
        this.patientId = patientId;
        this.reservedAt = reservedAt;
    }

    public UUID getId() { return id; }
    public UUID getSlotId() { return slotId; }
    public UUID getPatientId() { return patientId; }
    public LocalDateTime getReservedAt() { return reservedAt; }
}