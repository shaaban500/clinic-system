package clinic.appointmentBooking.internal.infrastructure.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "patients", schema = "appointment-booking")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "patient_name", nullable = false)
    private String patientName;

    public PatientEntity() {
    }

    public PatientEntity(UUID id, String patientName) {
        this.id = id;
        this.patientName = patientName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}
