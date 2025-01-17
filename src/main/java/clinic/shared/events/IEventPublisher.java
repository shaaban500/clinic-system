package clinic.shared.events;

import org.springframework.stereotype.Component;

@Component
public interface IEventPublisher {
    void publish(IDomainEvent event);
}
