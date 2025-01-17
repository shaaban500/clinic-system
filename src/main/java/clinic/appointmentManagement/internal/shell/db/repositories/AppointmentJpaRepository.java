package clinic.appointmentManagement.internal.shell.db.repositories;

import clinic.appointmentManagement.internal.core.services.dtos.AppointmentDto;
import clinic.appointmentManagement.internal.shell.db.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentJpaRepository extends JpaRepository<AppointmentEntity, UUID> {
    @Query(value = "SELECT * FROM appointments WHERE date_time >= :dateTime", nativeQuery = true)
    List<AppointmentDto> getUpComingAppointments(LocalDateTime dateTime);
}
