package unimiskolc.java.hotel_booking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import unimiskolc.java.hotel_booking.model.Booking;
import unimiskolc.java.hotel_booking.model.Guest;
import unimiskolc.java.hotel_booking.model.HotelService;
import unimiskolc.java.hotel_booking.model.Room;
import unimiskolc.java.hotel_booking.repository.BookingRepository;
import unimiskolc.java.hotel_booking.repository.GuestRepository;
import unimiskolc.java.hotel_booking.repository.HotelServiceRepository;
import unimiskolc.java.hotel_booking.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final HotelServiceRepository hotelServiceRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final BookingRepository bookingRepository;

    public DataInitializer(HotelServiceRepository hotelServiceRepository, RoomRepository roomRepository,
                           GuestRepository guestRepository, BookingRepository bookingRepository) {
        this.hotelServiceRepository = hotelServiceRepository;
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void run(String... args) {
        // Add test data for HotelService
        HotelService service1 = new HotelService();
        service1.setName("Spa");
        service1.setDescription("Relaxing spa service");
        service1.setPrice(50.0);
        hotelServiceRepository.save(service1);

        HotelService service2 = new HotelService();
        service2.setName("Gym");
        service2.setDescription("Access to the gym");
        service2.setPrice(20.0);
        hotelServiceRepository.save(service2);

        // Add test data for Room
        Room room1 = new Room();
        room1.setRoomNumber("101");
        room1.setCapacity(2);
        room1.setPricePerNight(100.0);
        roomRepository.save(room1);

        Room room2 = new Room();
        room2.setRoomNumber("102");
        room2.setCapacity(4);
        room2.setPricePerNight(150.0);
        roomRepository.save(room2);

        // Add test data for Guest
        Guest guest1 = new Guest();
        guest1.setName("John Doe");
        guest1.setEmail("john.doe@example.com");
        guest1.setPhoneNumber("123456789");
        guestRepository.save(guest1);

        Guest guest2 = new Guest();
        guest2.setName("Jane Smith");
        guest2.setEmail("jane.smith@example.com");
        guest2.setPhoneNumber("987654321");
        guestRepository.save(guest2);

        // Add test data for Booking
        Booking booking1 = new Booking();
        booking1.setRoom(room1);
        booking1.setGuest(guest1);
        booking1.setCheckInDate(LocalDate.now());
        booking1.setCheckOutDate(LocalDate.now().plusDays(3));
        booking1.setHotelServices(List.of(service1));
        bookingRepository.save(booking1);

        Booking booking2 = new Booking();
        booking2.setRoom(room2);
        booking2.setGuest(guest2);
        booking2.setCheckInDate(LocalDate.now().plusDays(1));
        booking2.setCheckOutDate(LocalDate.now().plusDays(5));
        bookingRepository.save(booking2);

        System.out.println("Test data initialized.");
    }
}