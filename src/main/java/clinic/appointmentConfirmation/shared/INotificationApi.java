package clinic.appointmentConfirmation.shared;

public interface INotificationApi {
    void notify(Object payload, String topicName);
}
