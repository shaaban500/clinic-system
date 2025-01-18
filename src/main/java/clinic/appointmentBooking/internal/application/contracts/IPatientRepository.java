package clinic.appointmentBooking.internal.application.contracts;

import clinic.appointmentBooking.internal.domain.models.Patient;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPatientRepository {
    Patient findById(UUID id);
    void Save(Patient patient);
}
