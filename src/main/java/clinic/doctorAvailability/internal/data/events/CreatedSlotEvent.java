package clinic.doctorAvailability.internal.data.events;

import clinic.doctorAvailability.shared.ISlotCreatedEvent;
import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreatedSlotEvent extends ApplicationEvent implements ISlotCreatedEvent {
    private final UUID id;
    private final LocalDateTime dateTime;
    private final Boolean isReserved;
    private final BigDecimal cost;

    public CreatedSlotEvent(Object source, UUID id, LocalDateTime dateTime, Boolean isReserved, BigDecimal cost) {
        super(source);
        this.id = id;
        this.dateTime = dateTime;
        this.isReserved = isReserved;
        this.cost = cost;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public Boolean getIsReserved() {
        return isReserved;
    }

    @Override
    public BigDecimal getCost() {
        return cost;
    }
}
