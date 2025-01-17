package clinic.appointmentManagement.internal.shell.events.eventHandlers;


import clinic.appointmentBooking.shared.BookedSlotEvent;
import clinic.appointmentManagement.internal.core.models.Appointment;
import clinic.appointmentManagement.internal.shell.db.repositories.AppointmentRepository;
import org.springframework.context.event.EventListener;

public class BookedSlotEventHandler {
    private final AppointmentRepository appointmentRepository;
    public BookedSlotEventHandler(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @EventListener
    public void handleCreatedSlotEvent(BookedSlotEvent event) {
        Appointment appointment = new Appointment(
                null,
                event.slotId(),
                event.patientId(),
                null,
                event.patientName(),
                event.dateTime(),
                event.reservedAt());

        appointmentRepository.save(appointment);
    }

}
