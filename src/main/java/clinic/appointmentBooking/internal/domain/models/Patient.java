package clinic.appointmentBooking.internal.domain.models;

import java.util.UUID;

public class Patient {
    private UUID id;
    private String patientName;

    public Patient(UUID id, String patientName) {
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
