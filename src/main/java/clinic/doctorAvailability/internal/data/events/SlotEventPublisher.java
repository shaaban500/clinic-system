package clinic.doctorAvailability.internal.data.events;

import clinic.doctorAvailability.internal.data.entities.SlotEntity;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SlotEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public SlotEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishCreatedSlotEvent(SlotEntity slotEntity) {
        CreatedSlotEvent event = new CreatedSlotEvent(
                this,
                slotEntity.getId(),
                slotEntity.getDateTime(),
                slotEntity.getIsReserved(),
                slotEntity.getCost());

        applicationEventPublisher.publishEvent(event);
    }
}
