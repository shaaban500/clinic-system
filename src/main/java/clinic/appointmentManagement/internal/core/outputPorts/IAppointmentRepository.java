package clinic.appointmentManagement.internal.core.outputPorts;//package com.appointmentManagement.core.outputPorts;

import clinic.appointmentManagement.internal.core.models.Appointment;
import clinic.appointmentManagement.internal.core.services.dtos.AppointmentDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAppointmentRepository {
    List<AppointmentDto> getUpComingAppointments();
    void save(Appointment appointment);

    Appointment findById(UUID id);
}
