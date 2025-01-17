package clinic.appointmentBooking.internal.application.services;

import clinic.appointmentBooking.internal.application.contracts.IPatientRepository;
import clinic.appointmentBooking.internal.application.contracts.ISlotRepository;
import clinic.appointmentBooking.internal.domain.models.Patient;
import clinic.appointmentBooking.internal.domain.models.SlotDomain;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookSlotHandler {

    private final ISlotRepository slotRepository;
    private final IPatientRepository patientRepository;
    public BookSlotHandler(ISlotRepository slotRepository, IPatientRepository patientRepository) {
        this.slotRepository = slotRepository;
        this.patientRepository = patientRepository;
    }

    public void handle(UUID slotId, UUID patientId) {
        SlotDomain slot = slotRepository.findById(slotId).orElseThrow();
        Patient patient = patientRepository.findById(patientId);

        slot.markReserved(patient.getId(), patient.getPatientName());
        slotRepository.save(slot);
    }
}
