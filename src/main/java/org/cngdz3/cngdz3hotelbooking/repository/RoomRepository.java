package org.cngdz3.cngdz3hotelbooking.repository;

import org.cngdz3.cngdz3hotelbooking.model.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoomRepository {
    private final Map<Long, Room> rooms = new HashMap<>();
    private long idCounter = 1;

    public List<Room> findAll() {
        return new ArrayList<>(rooms.values());
    }

    public Room findById(Long id) {
        return rooms.get(id);
    }

    public Room save(Room room) {
        if (room.getId() == null) {
            room.setId(idCounter++);
        }
        rooms.put(room.getId(), room);
        return room;
    }

    public void deleteById(Long id) {
        rooms.remove(id);
    }

    public List<Room> findByCapacity(int capacity) {
        List<Room> result = new ArrayList<>();
        for (Room room : rooms.values()) {
            if (room.getCapacity() == capacity) {
                result.add(room);
            }
        }
        return result;
    }
}