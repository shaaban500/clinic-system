package clinic.appointmentBooking.internal.infrastructure.repositories;

import clinic.appointmentBooking.internal.application.contracts.IPatientRepository;
import clinic.appointmentBooking.internal.domain.models.Patient;
import clinic.appointmentBooking.internal.infrastructure.entities.PatientEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class PatientRepository implements IPatientRepository {
    private final JpaPatientRepository jpaPatientRepository;
    public PatientRepository(JpaPatientRepository jpaPatientRepository) {
        this.jpaPatientRepository = jpaPatientRepository;
    }

    @Override
    public Patient findById(UUID id) {
        return mapToPatientDomain(jpaPatientRepository.findById(id).orElseThrow());
    }

    @Override
    public void Save(Patient patient) {
        PatientEntity entity = mapToPatientEntity(patient);
        jpaPatientRepository.save(entity);
    }

    private PatientEntity mapToPatientEntity(Patient patient) {
        return new PatientEntity(patient.getId(), patient.getPatientName());
    }

    private Patient mapToPatientDomain(PatientEntity entity) {
        return new Patient(entity.getId(), entity.getPatientName());
    }
}
