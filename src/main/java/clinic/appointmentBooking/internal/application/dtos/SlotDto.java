package clinic.appointmentBooking.internal.application.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface SlotDto {
        UUID getId();
        LocalDateTime getDateTime();
        Boolean getIsReserved();
        BigDecimal getCost();
}