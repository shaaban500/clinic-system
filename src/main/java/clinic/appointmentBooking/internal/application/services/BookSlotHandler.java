package clinic.appointmentBooking.internal.application.services;

import clinic.appointmentBooking.internal.application.contracts.ISlotRepository;
import clinic.appointmentBooking.internal.domain.models.SlotDomain;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookSlotHandler {

    private final ISlotRepository slotRepository;

    public BookSlotHandler(ISlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public void handle(UUID slotId) {
        SlotDomain slot = slotRepository.findById(slotId).orElse(null);

        if (slot == null) {
            throw new IllegalArgumentException("this slot is not exist");
        }

        slot.markReserved();
        slotRepository.save(slot);
        // todo: 1. get slot
        // todo: 2. check slot is available
        // todo: 3. update slot
        // todo: 4. send event to appointments module 3
        // todo: 5. send notification event
    }
}
