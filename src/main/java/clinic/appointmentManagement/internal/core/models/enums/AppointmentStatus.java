package clinic.appointmentManagement.internal.core.models.enums;

public enum AppointmentStatus {
    CANCELED("Canceled", 1),
    COMPLETED("Completed", 2);

    private Integer id;
    private String status;

    AppointmentStatus(String status, Integer id) {
        this.id = id;
        this.status = status;
    }

    public static AppointmentStatus getStatusById(Integer id) {
        for (AppointmentStatus status : AppointmentStatus.values()) {
            if (status.getId().equals(id)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid ID: " + id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
