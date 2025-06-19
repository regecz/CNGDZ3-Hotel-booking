package unimiskolc.java.hotel_booking.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unimiskolc.java.hotel_booking.model.Room;
import unimiskolc.java.hotel_booking.repository.RoomRepository;

import java.util.List;

@Service
@Transactional
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void addRoom(Room room) {
        room.setId(null);
        roomRepository.save(room);
    }

    public void updateRoom(Room room) {
        if (room.getId() == null) {
            throw new IllegalArgumentException("Room ID cannot be null for update");
        }
        if (!roomRepository.existsById(room.getId())) {
            throw new IllegalArgumentException("Room not found with ID: " + room.getId());
        }
        roomRepository.save(room);
    }

    public void deleteRoom(Long id) {
        roomRepository.findById(id)
                .ifPresent(roomRepository::delete);
    }

    public Room findRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room not found with ID: " + id));
    }

    public List<Room> listRooms() {
        return roomRepository.findAll();
    }
}