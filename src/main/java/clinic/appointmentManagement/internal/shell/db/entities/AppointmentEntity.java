package clinic.appointmentManagement.internal.shell.db.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "AppointmentEntity")
@Table(name = "appointments", schema = "appointment-management")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "slot_id", nullable = false)
    private UUID slotId;

    @Column(name = "patient_id", nullable = false)
    private UUID patientId;

    @Column(name = "reserved_at", nullable = false)
    private LocalDateTime reservedAt;

    @Column(name = "status_id", nullable = true)
    private Integer statusId;

    @Column(name = "patient_name", nullable = false)
    private String patientName;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    public AppointmentEntity() {
    }

    public AppointmentEntity(UUID id, UUID slotId, UUID patientId,
                             LocalDateTime reservedAt, Integer statusId,
                             String patientName, LocalDateTime dateTime) {
        this.id = id;
        this.slotId = slotId;
        this.patientId = patientId;
        this.reservedAt = reservedAt;
        this.statusId = statusId;
        this.patientName = patientName;
        this.dateTime = dateTime;
    }

    public UUID getId() { return id; }
    public UUID getSlotId() { return slotId; }
    public UUID getPatientId() { return patientId; }
    public LocalDateTime getReservedAt() { return reservedAt; }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}