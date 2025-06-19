package unimiskolc.java.hotel_booking.repository;

import unimiskolc.java.hotel_booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}