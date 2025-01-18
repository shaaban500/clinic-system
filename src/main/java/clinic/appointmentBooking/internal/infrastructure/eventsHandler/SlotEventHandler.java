package clinic.appointmentBooking.internal.infrastructure.eventsHandler;

import clinic.appointmentBooking.internal.application.contracts.ISlotRepository;
import clinic.appointmentBooking.shared.BookedSlotEvent;
import clinic.appointmentBooking.internal.domain.models.SlotDomain;
import clinic.appointmentBooking.internal.infrastructure.repositories.SlotRepository;
import clinic.appointmentConfirmation.shared.INotificationApi;
import clinic.appointmentManagement.shared.events.CanceledAppointmentEvent;
import clinic.doctorAvailability.shared.CreatedSlotEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static clinic.appointmentBooking.internal.domain.models.SlotDomain.createSlotDomain;

@Component
public class SlotEventHandler {
    private final ISlotRepository slotRepository;
    private final INotificationApi notificationApi;
    public SlotEventHandler(ISlotRepository slotRepository, INotificationApi notificationApi) {
        this.slotRepository = slotRepository;
        this.notificationApi = notificationApi;
    }

    @EventListener
    public void handleCreatedSlotEvent(CreatedSlotEvent event) {
        SlotDomain slotDomain = createSlotDomain(
                event.id(),
                event.dateTime(),
                event.isReserved(),
                event.cost());

        slotRepository.save(slotDomain);
    }

    @EventListener
    public void handleBookedSlotEvent(BookedSlotEvent event) {
        notificationApi.notify(event, "Booking");
    }

    @EventListener
    public void handleCanceledSlotEvent(CanceledAppointmentEvent event) {
        var slot = slotRepository.findById(event.slotId()).orElseThrow();
        slot.setIsReserved(false);
        slotRepository.save(slot);
    }
}
