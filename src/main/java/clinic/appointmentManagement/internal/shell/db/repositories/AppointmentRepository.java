package clinic.appointmentManagement.internal.shell.db.repositories;

import clinic.appointmentManagement.internal.core.models.Appointment;
import clinic.appointmentManagement.internal.core.outputPorts.IAppointmentRepository;
import clinic.appointmentManagement.internal.core.services.dtos.AppointmentDto;
import clinic.appointmentManagement.internal.shell.db.entities.AppointmentEntity;
import clinic.shared.events.IEventPublisher;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class AppointmentRepository implements IAppointmentRepository {
    private final IEventPublisher publisher;
    private final AppointmentJpaRepository appointmentJpaRepository;

    public AppointmentRepository(IEventPublisher publisher, AppointmentJpaRepository appointmentJpaRepository) {
        this.publisher = publisher;
        this.appointmentJpaRepository = appointmentJpaRepository;
    }

    @Override
    public List<AppointmentDto> getUpComingAppointments() {
        return appointmentJpaRepository.getUpComingAppointments(LocalDateTime.now());
    }

    @Override
    public void save(Appointment appointment) {
        AppointmentEntity entity = mapToAppointmentEntity(appointment);
        appointmentJpaRepository.save(entity);
        appointment.getEvents().forEach(publisher::publish);
    }

    @Override
    public Appointment findById(UUID id) {
        AppointmentEntity entity = appointmentJpaRepository.findById(id).orElseThrow();
        return mapToAppointmentDomain(entity);
    }

    private AppointmentEntity mapToAppointmentEntity(Appointment appointment) {
        return new AppointmentEntity(
                appointment.getId(),
                appointment.getSlotId(),
                appointment.getPatientId(),
                appointment.getReservedAt(),
                appointment.getStatusId(),
                appointment.getPatientName(),
                appointment.getDateTime());
    }

    private Appointment mapToAppointmentDomain(AppointmentEntity entity) {
        return new Appointment(
                entity.getId(),
                entity.getSlotId(),
                entity.getPatientId(),
                entity.getStatusId(),
                entity.getPatientName(),
                entity.getDateTime(),
                entity.getReservedAt());
    }
}
