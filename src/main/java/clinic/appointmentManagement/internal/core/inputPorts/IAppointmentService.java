package clinic.appointmentManagement.internal.core.inputPorts;

import clinic.appointmentManagement.internal.core.services.dtos.AppointmentDto;

import java.util.List;
import java.util.UUID;

public interface IAppointmentService {
    List<AppointmentDto> getUpComingAppointments();
    void UpdateStatus(UUID appointmentId, Integer statusId);
}
