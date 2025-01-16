package clinic.appointmentBooking.infrastructure.events.eventsHandler;

import clinic.appointmentBooking.domain.models.SlotDomain;
import clinic.appointmentBooking.infrastructure.repositories.SlotRepository;
import clinic.doctorAvailability.shared.ISlotCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static clinic.appointmentBooking.domain.models.SlotDomain.createSlotDomain;

@Component
public class SlotEventHandler {

    private final SlotRepository slotRepository;

    public SlotEventHandler(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @EventListener
    public void handleCreatedSlotEvent(ISlotCreatedEvent event) {
        SlotDomain slotDomain = createSlotDomain(
                null,
                event.getDateTime(),
                event.getIsReserved(),
                event.getCost());

        slotRepository.save(slotDomain);
    }
}
