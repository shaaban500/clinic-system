package clinic.appointmentManagement.shared.events;

import clinic.shared.events.IDomainEvent;

import java.util.UUID;

public record CanceledAppointmentEvent (UUID slotId) implements IDomainEvent {
}
