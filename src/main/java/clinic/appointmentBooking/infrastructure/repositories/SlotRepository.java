package clinic.appointmentBooking.infrastructure.repositories;

import clinic.appointmentBooking.application.contracts.ISlotRepository;
import clinic.appointmentBooking.application.dtos.SlotDto;
import clinic.appointmentBooking.domain.models.SlotDomain;
import clinic.appointmentBooking.infrastructure.entities.SlotEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SlotRepository implements ISlotRepository {

    private final JpaSlotRepository jpaSlotRepository;
    public SlotRepository(JpaSlotRepository jpaSlotRepository) {
        this.jpaSlotRepository = jpaSlotRepository;
    }

    @Override
    public List<SlotDto> findAvailableSlots(LocalDateTime dateTime) {
        return jpaSlotRepository.findAvailableSlots(dateTime);
    }

    @Override
    public Optional<SlotDomain> findById(UUID slotId) {
        return jpaSlotRepository.findById(slotId).stream()
                .map(this::mapToDomain).findFirst();
    }

    @Override
    public void save(SlotDomain slotDomain) {
        SlotEntity entity = mapToEntity(slotDomain);
        System.out.println("--------------");
        jpaSlotRepository.save(entity);
    }

    private SlotDomain mapToDomain(SlotEntity entity) {
        return SlotDomain.createSlotDomain(
                entity.getId(),
                entity.getDateTime(),
                entity.getIsReserved(),
                entity.getCost());
    }

    private SlotEntity mapToEntity(SlotDomain slotDomain) {
        return new SlotEntity(
                slotDomain.getId(),
                slotDomain.getDateTime(),
                slotDomain.getIsReserved(),
                slotDomain.getCost()
                );
    }
}
