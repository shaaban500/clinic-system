package clinic.appointmentBooking.internal.application.contracts;

import clinic.appointmentBooking.internal.application.dtos.SlotDto;
import clinic.appointmentBooking.internal.domain.models.SlotDomain;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ISlotRepository {
    List<SlotDto> findAvailableSlots(LocalDateTime dateTime);
    Optional<SlotDomain> findById(UUID slotId);
    void save(SlotDomain slotDomain);
}
