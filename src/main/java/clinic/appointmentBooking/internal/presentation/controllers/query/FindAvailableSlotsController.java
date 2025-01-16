package clinic.appointmentBooking.internal.presentation.controllers.query;

import clinic.appointmentBooking.internal.application.dtos.SlotDto;
import clinic.appointmentBooking.internal.application.services.FindAvailableSlotsHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/slots")
public class FindAvailableSlotsController {
    private final FindAvailableSlotsHandler findAvailableSlotsHandler;

    public FindAvailableSlotsController(FindAvailableSlotsHandler findAvailableSlotsHandler) {
        this.findAvailableSlotsHandler = findAvailableSlotsHandler;
    }

    @GetMapping("/available")
    public ResponseEntity<List<SlotDto>> FindAvailableSlots() {
        var slots = findAvailableSlotsHandler.handle();
        return ResponseEntity.ok(slots);
    }


}
