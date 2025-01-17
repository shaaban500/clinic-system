package clinic.appointmentBooking.internal.application.contracts;

import clinic.appointmentBooking.internal.domain.models.Patient;

import java.util.UUID;

public interface IPatientRepository {
    Patient findById(UUID id);
    void Save(Patient patient);
}
