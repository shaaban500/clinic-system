package clinic.appointmentManagement.internal.core.services.enums;

public enum AppointmentStatus {
    CANCELED("Canceled", 1),
    COMPLETED("Completed", 1);

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
