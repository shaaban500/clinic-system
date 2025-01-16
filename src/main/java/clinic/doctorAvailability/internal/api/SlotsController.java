package clinic.doctorAvailability.internal.api;

import clinic.doctorAvailability.internal.service.SlotsService;
import clinic.doctorAvailability.internal.service.dtos.SlotDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/slots")
public class SlotsController {
    private final SlotsService slotsService;

    SlotsController(SlotsService slotsService) {
        this.slotsService = slotsService;
    }

    @GetMapping
    public ResponseEntity<List<SlotDto>> getAllSlots() {
        List<SlotDto> slots = slotsService.getAllSlots();
        return ResponseEntity.ok(slots);
    }

    @PostMapping
    public ResponseEntity createSlot(@RequestBody SlotDto slotDto) {
        slotsService.createSlot(slotDto);
        return ResponseEntity.ok().build();
    }
}
