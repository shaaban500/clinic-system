package clinic.appointmentBooking.internal.infrastructure.eventsHandler;

import clinic.appointmentBooking.shared.BookedSlotEvent;
import clinic.appointmentBooking.internal.domain.models.SlotDomain;
import clinic.appointmentBooking.internal.infrastructure.repositories.SlotRepository;
import clinic.appointmentConfirmation.shared.INotificationApi;
import clinic.doctorAvailability.shared.ISlotCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static clinic.appointmentBooking.internal.domain.models.SlotDomain.createSlotDomain;

@Component
public class SlotEventHandler {
    private final SlotRepository slotRepository;
    private final INotificationApi notificationApi;
    public SlotEventHandler(SlotRepository slotRepository, INotificationApi notificationApi) {
        this.slotRepository = slotRepository;
        this.notificationApi = notificationApi;
    }

    @EventListener
    public void handleCreatedSlotEvent(ISlotCreatedEvent event) {
        SlotDomain slotDomain = createSlotDomain(
                event.getId(),
                event.getDateTime(),
                event.getIsReserved(),
                event.getCost());

        slotRepository.save(slotDomain);
    }

    @EventListener
    public void handleBookedSlotEvent(BookedSlotEvent event) {
        SlotDomain slotDomain = slotRepository.findById(event.id()).orElseThrow();
        //notificationApi.notify();
    }

}
