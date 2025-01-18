package clinic.doctorAvailability.internal.service;

import clinic.doctorAvailability.internal.data.dtos.SlotEntityDto;
import clinic.doctorAvailability.internal.data.entities.SlotEntity;
import clinic.doctorAvailability.internal.data.repositories.SlotRepository;
import clinic.doctorAvailability.internal.service.dtos.SlotDto;
import clinic.doctorAvailability.shared.CreatedSlotEvent;
import clinic.shared.events.IEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SlotsService {
    private final SlotRepository slotRepository;
    private final IEventPublisher eventPublisher;

    SlotsService(SlotRepository slotRepository, IEventPublisher eventPublisher) {
        this.slotRepository = slotRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<SlotDto> getAllSlots() {
        List<SlotEntityDto> slots = slotRepository.findAllUpcomingSlots(LocalDateTime.now());
        return mapToSlotDto(slots);
    }

    public void createSlot(SlotDto newSlot) {
        SlotEntityDto existingSlotEntity = slotRepository.findByDateTime(newSlot.getDateTime()).orElse(null);

        if (existingSlotEntity != null) {
            throw new IllegalArgumentException("There is already a slot with the same date and time.");
        }

        SlotEntity slotEntity = mapToSlotEntity(newSlot);
        var createdSlot = slotRepository.save(slotEntity);

        eventPublisher.publish(new CreatedSlotEvent(
                createdSlot.getId(),
                createdSlot.getDateTime(),
                createdSlot.getIsReserved(),
                createdSlot.getCost()));
    }

    private List<SlotDto> mapToSlotDto(List<SlotEntityDto> slots) {
        return slots.stream()
                .map(slot -> {
                    SlotDto dto = new SlotDto();
                    dto.setId(slot.getId());
                    dto.setCost(slot.getCost());
                    dto.setDateTime(slot.getDateTime());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private SlotEntity mapToSlotEntity(SlotDto slot) {
        SlotEntity entity = new SlotEntity();
        entity.setId(slot.getId());
        entity.setCost(slot.getCost());
        entity.setDateTime(slot.getDateTime());
        return entity;
    }

}
