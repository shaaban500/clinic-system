package clinic.appointmentConfirmation.shared;

import org.springframework.stereotype.Component;

@Component
public interface INotificationApi {
    void notify(Object payload, String topicName);
}
