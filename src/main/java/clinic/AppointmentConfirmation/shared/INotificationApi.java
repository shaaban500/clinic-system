package clinic.AppointmentConfirmation.shared;

public interface INotificationApi {
    void notify(Object payload, String topicName);
}
