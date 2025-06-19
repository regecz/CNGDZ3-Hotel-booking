package unimiskolc.java.hotel_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unimiskolc.java.hotel_booking.model.HotelService;

public interface HotelServiceRepository extends JpaRepository<HotelService, Long> {

}