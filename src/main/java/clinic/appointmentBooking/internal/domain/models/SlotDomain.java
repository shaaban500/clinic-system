package clinic.appointmentBooking.internal.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class SlotDomain {
    private UUID id;
    private LocalDateTime dateTime;
    private Boolean isReserved;
    private BigDecimal cost;

    private SlotDomain(UUID id, LocalDateTime dateTime, Boolean isReserved, BigDecimal cost) {
        this.id = id;
        this.dateTime = dateTime;
        this.isReserved = isReserved;
        this.cost = cost;
    }

    public static SlotDomain createSlotDomain(UUID id, LocalDateTime dateTime, Boolean isReserved, BigDecimal cost) {
        return new SlotDomain(id, dateTime, isReserved, cost);
    }

    public UUID getId() {
        return this.id;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public Boolean getIsReserved() {
        return this.isReserved;
    }

    public BigDecimal getCost() {
        return this.cost;
    }

    public void setDateTime(LocalDateTime date) {
        this.dateTime = date;
    }

    public void setIsReserved(Boolean isReserved) {
        this.isReserved = isReserved;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void markReserved() {
        if (this.isReserved) {
            throw new IllegalStateException("this slot is already reserved.");
        }
        this.isReserved = true;
    }
}
