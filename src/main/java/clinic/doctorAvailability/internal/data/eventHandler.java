package clinic.doctorAvailability.internal.data;

import clinic.appointmentBooking.shared.BookedSlotEvent;
import clinic.appointmentManagement.shared.events.CanceledAppointmentEvent;
import clinic.doctorAvailability.internal.data.repositories.SlotRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class eventHandler {
    private final SlotRepository slotRepository;

    public eventHandler(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @EventListener
    public void handleBookedSlotEvent(BookedSlotEvent event) {
        var slot = slotRepository.findById(event.slotId()).orElseThrow();
        slot.setIsReserved(true);
        slotRepository.save(slot);
    }

    @EventListener
    public void handleCanceledSlotEvent(CanceledAppointmentEvent event) {
        var slot = slotRepository.findById(event.slotId()).orElseThrow();
        slot.setIsReserved(false);
        slotRepository.save(slot);
    }
}
