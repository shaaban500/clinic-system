package clinic.doctorAvailability.internal.data.repositories;

import clinic.doctorAvailability.internal.data.dtos.SlotEntityDto;
import clinic.doctorAvailability.internal.data.entities.SlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("doctorAvailabilitySlotRepository")
public interface SlotRepository extends JpaRepository<SlotEntity, UUID> {
    @Query(value = "SELECT * FROM slots WHERE date_time >= :dateTime", nativeQuery = true)
    List<SlotEntityDto> findAllUpcomingSlots(LocalDateTime dateTime);

    @Query(value = "SELECT * FROM slots WHERE date_time = :dateTime LIMIT 1", nativeQuery = true)
    Optional<SlotEntityDto> findByDateTime(LocalDateTime dateTime);
}
