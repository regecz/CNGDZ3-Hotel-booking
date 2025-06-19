package unimiskolc.java.hotel_booking.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unimiskolc.java.hotel_booking.model.Booking;
import unimiskolc.java.hotel_booking.model.Guest;
import unimiskolc.java.hotel_booking.model.HotelService;
import unimiskolc.java.hotel_booking.model.Room;
import unimiskolc.java.hotel_booking.repository.BookingRepository;
import unimiskolc.java.hotel_booking.repository.GuestRepository;
import unimiskolc.java.hotel_booking.repository.RoomRepository;
import unimiskolc.java.hotel_booking.repository.HotelServiceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookingService {
    private final BookingRepository bookingRepository;
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;
    private final HotelServiceRepository hotelServiceRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          GuestRepository guestRepository,
                          RoomRepository roomRepository,
                          HotelServiceRepository hotelServiceRepository) {
        this.bookingRepository = bookingRepository;
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
        this.hotelServiceRepository = hotelServiceRepository;
    }

    public void addBooking(Booking booking) {
        validateBooking(booking);
        booking.setId(null);
        bookingRepository.save(booking);
    }

    public void updateBooking(Booking booking) {
        if (booking.getId() == null) {
            throw new IllegalArgumentException("Booking ID cannot be null for update");
        }
        validateBooking(booking);
        System.out.println(booking);
        System.out.println("Updating booking with ID: " + booking.getId());
        bookingRepository.save(booking);
    }

    private void validateBooking(Booking booking) {
        Guest guest = guestRepository.findById(booking.getGuest().getId())
                .orElseThrow(() -> new IllegalArgumentException("Guest not found with ID: " + booking.getGuest().getId()));
        booking.setGuest(guest);

        Room room = roomRepository.findById(booking.getRoom().getId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found with ID: " + booking.getRoom().getId()));
        booking.setRoom(room);

        if (booking.getHotelServices() != null && !booking.getHotelServices().isEmpty()) {
            List<HotelService> validatedHotelServices = new ArrayList<>();
            for (HotelService hotelService : booking.getHotelServices()) {
                HotelService validHotelService = hotelServiceRepository.findById(hotelService.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Service not found with ID: " + hotelService.getId()));
                validatedHotelServices.add(validHotelService);
            }
            booking.setHotelServices(validatedHotelServices);
        }

        if (booking.getCheckInDate() == null || booking.getCheckOutDate() == null) {
            throw new IllegalArgumentException("Check-in and check-out dates are required");
        }
        if (booking.getCheckOutDate().isBefore(booking.getCheckInDate())) {
            throw new IllegalArgumentException("Check-out date cannot be before check-in date");
        }
    }

    public void deleteBooking(Long id) {
        bookingRepository.findById(id)
                .ifPresent(bookingRepository::delete);
    }

    public List<Booking> listBookings() {
        return bookingRepository.findAll();
    }

    public Booking findBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with ID: " + id));
    }
}