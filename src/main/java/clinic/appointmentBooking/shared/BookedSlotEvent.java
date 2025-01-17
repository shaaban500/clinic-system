package clinic.appointmentBooking.shared;

import clinic.shared.events.IDomainEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookedSlotEvent(
        UUID slotId,
        UUID patientId,
        LocalDateTime reservedAt,
        String patientName,
        LocalDateTime dateTime) implements IDomainEvent {
}