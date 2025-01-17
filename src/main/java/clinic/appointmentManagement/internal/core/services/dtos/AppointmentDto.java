package clinic.appointmentManagement.internal.core.services.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public interface AppointmentDto {
    public UUID getId();
    public UUID getSlotId();
    public UUID getPatientId();
    public Integer getStatusId();
    public String getPatientName();
    public LocalDateTime getDateTime();
    public LocalDateTime getReservedAt();
}
