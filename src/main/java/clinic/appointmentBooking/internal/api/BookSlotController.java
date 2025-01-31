package clinic.appointmentBooking.internal.api;

import clinic.appointmentBooking.internal.application.commands.BookSlotHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/slots")
public class BookSlotController {

    private final BookSlotHandler bookSlotHandler;
    public BookSlotController(BookSlotHandler bookSlotHandler) {
        this.bookSlotHandler = bookSlotHandler;
    }

    @PostMapping("/book/{slotId}")
    public ResponseEntity<?> bookSlot(@PathVariable UUID slotId, @RequestParam(name = "patient_id", required = true) UUID patientId) {
        bookSlotHandler.handle(slotId, patientId);
        return ResponseEntity.ok().build();
    }
}
