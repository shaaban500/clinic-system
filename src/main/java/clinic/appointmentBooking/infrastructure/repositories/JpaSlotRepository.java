package clinic.appointmentBooking.infrastructure.repositories;

import clinic.appointmentBooking.application.dtos.SlotDto;
import clinic.appointmentBooking.infrastructure.entities.SlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("appointmentBookingSlotRepository")
public interface JpaSlotRepository extends JpaRepository<SlotEntity, UUID> {
    @Query(value = "SELECT * FROM slots WHERE date_time >= :dateTime AND is_reserved = false", nativeQuery = true)
    List<SlotDto> findAvailableSlots(LocalDateTime dateTime);

    @Query(value = "UPDATE slots SET is_reserved = true WHERE id = :slotId", nativeQuery = true)
    Optional<SlotEntity> bookASlot(UUID slotId);
}