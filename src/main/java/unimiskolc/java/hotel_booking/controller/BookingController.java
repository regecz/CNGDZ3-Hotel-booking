package unimiskolc.java.hotel_booking.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import unimiskolc.java.hotel_booking.dto.BookingDTO;
import unimiskolc.java.hotel_booking.model.Booking;
import unimiskolc.java.hotel_booking.model.Guest;
import unimiskolc.java.hotel_booking.model.HotelService;
import unimiskolc.java.hotel_booking.model.Room;
import unimiskolc.java.hotel_booking.service.BookingService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(summary = "Add a new booking")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addBooking(@RequestBody BookingDTO bookingDTO) {
        Booking booking = convertToEntity(bookingDTO);
        bookingService.addBooking(booking);
    }

    @Operation(summary = "Update an existing booking")
    @PutMapping("/{id}")
    public void updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        Booking booking = convertToEntity(bookingDTO);
        booking.setId(id);
        System.out.println("Updating booking with ID: " + id);
        bookingService.updateBooking(booking);
    }

    @Operation(summary = "Delete a booking")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }

    @Operation(summary = "Get a booking by ID")
    @GetMapping("/{id}")
    public BookingDTO getBookingById(@PathVariable Long id) {
        return convertToDTO(bookingService.findBookingById(id));
    }

    @Operation(summary = "List all bookings")
    @GetMapping
    public List<BookingDTO> listBookings() {
        List<Booking> bookings = bookingService.listBookings();
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingDTOs.add(convertToDTO(booking));
        }
        return bookingDTOs;
    }

    private Booking convertToEntity(BookingDTO dto) {
        Booking booking = new Booking();
        booking.setId(dto.getId());
        booking.setCheckInDate(dto.getCheckInDate());
        booking.setCheckOutDate(dto.getCheckOutDate());

        if (dto.getGuestId() != null) {
            Guest guest = new Guest();
            guest.setId(dto.getGuestId());
            booking.setGuest(guest);
        }

        if (dto.getRoomId() != null) {
            Room room = new Room();
            room.setId(dto.getRoomId());
            booking.setRoom(room);
        }

        if (dto.getServiceIds() != null && !dto.getServiceIds().isEmpty()) {
            List<HotelService> hotelServices = new ArrayList<>();
            for (Long serviceId : dto.getServiceIds()) {
                HotelService hotelService = new HotelService();
                hotelService.setId(serviceId);
                hotelServices.add(hotelService);
            }
            booking.setHotelServices(hotelServices);
        }

        return booking;
    }

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());

        if (booking.getGuest() != null) {
            dto.setGuestId(booking.getGuest().getId());
        }

        if (booking.getRoom() != null) {
            dto.setRoomId(booking.getRoom().getId());
        }

        if (booking.getHotelServices() != null && !booking.getHotelServices().isEmpty()) {
            List<Long> serviceIds = new ArrayList<>();
            for (HotelService service : booking.getHotelServices()) {
                serviceIds.add(service.getId());
            }
            dto.setServiceIds(serviceIds);
        }

        return dto;
    }
}