package org.cngdz3.cngdz3hotelbooking.service;

import org.cngdz3.cngdz3hotelbooking.model.Room;
import org.cngdz3.cngdz3hotelbooking.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }

    public List<Room> findRoomsByCapacity(int capacity) {
        return roomRepository.findByCapacity(capacity);
    }
}