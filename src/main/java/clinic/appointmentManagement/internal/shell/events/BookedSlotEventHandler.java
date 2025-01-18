package clinic.appointmentManagement.internal.shell.events;

import clinic.appointmentBooking.shared.BookedSlotEvent;
import clinic.appointmentManagement.internal.core.models.Appointment;
import clinic.appointmentManagement.internal.core.outputPorts.IAppointmentRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class BookedSlotEventHandler {
    private final IAppointmentRepository appointmentRepository;
    public BookedSlotEventHandler(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @EventListener
    public void handleBookedSlotEvent(BookedSlotEvent event) {
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
