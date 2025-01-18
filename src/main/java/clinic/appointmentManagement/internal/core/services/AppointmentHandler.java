package clinic.appointmentManagement.internal.core.services;

import clinic.appointmentManagement.internal.core.inputPorts.IAppointmentService;
import clinic.appointmentManagement.internal.core.models.Appointment;
import clinic.appointmentManagement.internal.core.outputPorts.IAppointmentRepository;
import clinic.appointmentManagement.internal.core.services.dtos.AppointmentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentHandler implements IAppointmentService {
    private final IAppointmentRepository appointmentRepository;
    public AppointmentHandler(IAppointmentRepository appointmentRepository){
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<AppointmentDto> getUpComingAppointments() {
        return appointmentRepository.getUpComingAppointments();
    }

    @Override
    public void UpdateStatus(UUID appointmentId, Integer statusId) {
        Appointment appointment = appointmentRepository.findById(appointmentId);
        appointment.setStatusId(statusId);
        appointmentRepository.save(appointment);
    }
}
