package clinic.appointmentBooking.application.contracts;

import clinic.appointmentBooking.application.dtos.SlotDto;
import clinic.appointmentBooking.domain.models.SlotDomain;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface ISlotRepository {
    List<SlotDto> findAvailableSlots(LocalDateTime dateTime);
    Optional<SlotDomain> findById(UUID slotId);
    void save(SlotDomain slotDomain);
}
