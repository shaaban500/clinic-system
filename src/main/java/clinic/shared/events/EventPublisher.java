package clinic.shared.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher implements IEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public EventPublisher(ApplicationEventPublisher applicationEventPublisher){
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(IDomainEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
