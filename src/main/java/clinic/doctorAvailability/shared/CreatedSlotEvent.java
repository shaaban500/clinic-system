package clinic.doctorAvailability.shared;

import clinic.shared.events.IDomainEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreatedSlotEvent(UUID id,
                               LocalDateTime dateTime,
                               Boolean isReserved,
                               BigDecimal cost)
        implements IDomainEvent {
}
