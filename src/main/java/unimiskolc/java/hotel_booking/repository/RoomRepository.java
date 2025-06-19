package unimiskolc.java.hotel_booking.repository;

import unimiskolc.java.hotel_booking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {


}