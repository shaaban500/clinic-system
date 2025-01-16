package clinic.doctorAvailability.internal.data.dtos;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface SlotEntityDto {
    UUID getId();
    LocalDateTime getDateTime();
    Boolean getIsReserved();
    BigDecimal getCost();
}
