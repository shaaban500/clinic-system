package clinic.doctorAvailability.shared;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface ISlotCreatedEvent {
    UUID getId();
    LocalDateTime getDateTime();
    Boolean getIsReserved();
    BigDecimal getCost();
}
