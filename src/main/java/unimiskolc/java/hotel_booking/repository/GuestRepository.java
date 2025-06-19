package unimiskolc.java.hotel_booking.repository;

import unimiskolc.java.hotel_booking.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}