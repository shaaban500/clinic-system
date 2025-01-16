package clinic.appointmentBooking.internal.infrastructure.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "AppointmentSlot")
@Table(name = "slots")
public class SlotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "is_reserved", nullable = false)
    private Boolean isReserved;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    public SlotEntity() {
    }
    public SlotEntity(UUID id, LocalDateTime dateTime, Boolean isReserved, BigDecimal cost) {
        this.id = id;
        this.dateTime = dateTime;
        this.isReserved = isReserved;
        this.cost = cost;
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
}

