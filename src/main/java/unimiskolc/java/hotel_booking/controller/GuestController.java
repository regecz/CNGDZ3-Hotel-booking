package unimiskolc.java.hotel_booking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import unimiskolc.java.hotel_booking.dto.GuestDTO;
import unimiskolc.java.hotel_booking.exception.GuestNotFoundException;
import unimiskolc.java.hotel_booking.model.Guest;
import unimiskolc.java.hotel_booking.service.GuestService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/guests")
@Tag(name = "Guest Controller", description = "Guest management endpoints")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @Operation(summary = "Add a new guest")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody GuestDTO guestDTO) {
        Guest guest = convertToEntity(guestDTO);
        guestService.addGuest(guest);
    }

    @Operation(summary = "Update an existing guest")
    @PutMapping("/{id}")
    public void updateGuest(@PathVariable Long id, @RequestBody GuestDTO guestDTO) {
        Guest guest = convertToEntity(guestDTO);
        guest.setId(id);
        guestService.updateGuest(guest);
    }

    @Operation(summary = "Delete a guest")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGuest(@PathVariable Long id) {
        guestService.deleteGuest(id);
    }

    @Operation(summary = "Get a guest by ID")
    @GetMapping("/{id}")
    public GuestDTO getGuestById(@PathVariable Long id) {
        try {
            return convertToDTO(guestService.findGuestById(id));
        } catch (GuestNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Operation(summary = "List all guests")
    @GetMapping
    public List<GuestDTO> listGuests() {
        List<Guest> guests = guestService.listGuests();
        List<GuestDTO> guestDTOs = new ArrayList<>();
        for (Guest guest : guests) {
            guestDTOs.add(convertToDTO(guest));
        }
        return guestDTOs;
    }

    private Guest convertToEntity(GuestDTO dto) {
        Guest guest = new Guest();
        guest.setId(dto.getId());
        guest.setName(dto.getName());
        guest.setEmail(dto.getEmail());
        guest.setPhoneNumber(dto.getPhoneNumber());
        return guest;
    }

    private GuestDTO convertToDTO(Guest guest) {
        GuestDTO dto = new GuestDTO();
        dto.setId(guest.getId());
        dto.setName(guest.getName());
        dto.setEmail(guest.getEmail());
        dto.setPhoneNumber(guest.getPhoneNumber());
        return dto;
    }
}