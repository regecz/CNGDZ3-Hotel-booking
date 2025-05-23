package org.cngdz3.cngdz3hotelbooking.repository;

import org.cngdz3.cngdz3hotelbooking.model.Guest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GuestRepository {
    private final Map<Long, Guest> guests = new HashMap<>();
    private long idCounter = 1;

    public List<Guest> findAll() {
        return new ArrayList<>(guests.values());
    }

    public Guest findById(Long id) {
        return guests.get(id);
    }

    public Guest save(Guest guest) {
        if (guest.getId() == null) {
            guest.setId(idCounter++);
        }
        guests.put(guest.getId(), guest);
        return guest;
    }

    public void deleteById(Long id) {
        guests.remove(id);
    }
}