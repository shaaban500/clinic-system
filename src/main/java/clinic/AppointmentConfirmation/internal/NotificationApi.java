package clinic.AppointmentConfirmation.internal;

import clinic.AppointmentConfirmation.shared.INotificationApi;
import org.springframework.stereotype.Component;

@Component
public class NotificationApi implements INotificationApi {

    @Override
    public void notify(Object payload, String topicName) {
        System.out.println("Notifying topic: " + topicName + " with payload: " + payload);
    }
}