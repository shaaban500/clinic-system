package clinic.appointmentBooking.internal.application.queries;

import clinic.appointmentBooking.internal.application.contracts.ISlotRepository;
import clinic.appointmentBooking.internal.application.dtos.SlotDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FindAvailableSlotsHandler {
    private final ISlotRepository slotRepository;

    public FindAvailableSlotsHandler(ISlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public List<SlotDto> handle() {
        return slotRepository.findAvailableSlots(LocalDateTime.now());
    }
}
