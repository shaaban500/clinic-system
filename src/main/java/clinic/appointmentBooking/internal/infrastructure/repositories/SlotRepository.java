package clinic.appointmentBooking.internal.infrastructure.repositories;

import clinic.appointmentBooking.internal.application.contracts.ISlotRepository;
import clinic.appointmentBooking.internal.application.dtos.SlotDto;
import clinic.appointmentBooking.internal.domain.models.SlotDomain;
import clinic.appointmentBooking.internal.infrastructure.entities.SlotEntity;
import clinic.shared.events.IEventPublisher;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SlotRepository implements ISlotRepository {
    private final JpaSlotRepository jpaSlotRepository;
    private final IEventPublisher eventPublisher;

    public SlotRepository(JpaSlotRepository jpaSlotRepository, IEventPublisher eventPublisher) {
        this.jpaSlotRepository = jpaSlotRepository;
        this.eventPublisher = eventPublisher;
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
        jpaSlotRepository.save(entity);
        slotDomain.getEvents().forEach(eventPublisher::publish);
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
                slotDomain.getCost());
    }
}
