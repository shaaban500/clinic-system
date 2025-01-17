package clinic.appointmentBooking.internal.infrastructure.repositories;

import clinic.appointmentBooking.internal.infrastructure.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaPatientRepository extends JpaRepository<PatientEntity, UUID> {
}
