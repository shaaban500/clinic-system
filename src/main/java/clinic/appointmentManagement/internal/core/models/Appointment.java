package clinic.appointmentManagement.internal.core.models;//package com.appointmentManagement.core.models;

import clinic.appointmentManagement.internal.core.services.enums.AppointmentStatus;
import clinic.appointmentManagement.shared.events.CanceledAppointmentEvent;
import clinic.shared.events.IDomainEvent;

import java.time.LocalDateTime;
import java.util.*;

public class Appointment {
    private UUID id;
    private UUID slotId;
    private UUID patientId;
    private Integer statusId;
    private String patientName;
    private LocalDateTime dateTime;
    private LocalDateTime reservedAt;
    private List<IDomainEvent> events;

    public Appointment(UUID id, UUID slotId, UUID patientId, Integer statusId, String patientName, LocalDateTime dateTime, LocalDateTime reservedAt) {
        this.id = id;
        this.slotId = slotId;
        this.patientId = patientId;
        this.statusId = statusId;
        this.patientName = patientName;
        this.dateTime = dateTime;
        this.reservedAt = reservedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSlotId() {
        return slotId;
    }

    public void setSlotId(UUID slotId) {
        this.slotId = slotId;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(LocalDateTime reservedAt) {
        this.reservedAt = reservedAt;
    }

    public List<IDomainEvent> getEvents() {
        return events;
    }

    public void setEvents(List<IDomainEvent> events) {
        this.events = events;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        if (statusId != null) {
            throw new RuntimeException("can't change appointment status.");
        }

        var statusEnum = AppointmentStatus.getStatusById(statusId);
        if (statusEnum.equals(AppointmentStatus.CANCELED)) {
            events.add(new CanceledAppointmentEvent(slotId));
        }

        this.statusId = statusId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
