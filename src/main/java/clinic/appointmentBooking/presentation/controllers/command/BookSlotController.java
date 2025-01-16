package clinic.appointmentBooking.presentation.controllers.command;

import clinic.appointmentBooking.application.services.BookSlotHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/slots")
public class BookSlotController {

    private final BookSlotHandler bookSlotHandler;
    public BookSlotController(BookSlotHandler bookSlotHandler) {
        this.bookSlotHandler = bookSlotHandler;
    }

    @PostMapping("/book/{slotId}")
    public ResponseEntity<?> bookSlot(@PathVariable UUID slotId) {
        bookSlotHandler.handle(slotId);
        return ResponseEntity.ok().build();
    }
}
