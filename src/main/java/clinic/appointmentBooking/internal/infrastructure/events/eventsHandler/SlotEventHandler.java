package clinic.appointmentBooking.internal.infrastructure.events.eventsHandler;

import clinic.appointmentBooking.internal.domain.models.SlotDomain;
import clinic.appointmentBooking.internal.infrastructure.repositories.SlotRepository;
import clinic.doctorAvailability.shared.ISlotCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static clinic.appointmentBooking.internal.domain.models.SlotDomain.createSlotDomain;

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
