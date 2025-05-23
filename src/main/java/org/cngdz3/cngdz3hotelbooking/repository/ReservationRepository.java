package org.cngdz3.cngdz3hotelbooking.repository;

import org.cngdz3.cngdz3hotelbooking.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReservationRepository {
    private final Map<Long, Reservation> reservations = new HashMap<>();
    private long idCounter = 1;

    public List<Reservation> findAll() {
        return new ArrayList<>(reservations.values());
    }

    public Reservation findById(Long id) {
        return reservations.get(id);
    }

    public Reservation save(Reservation reservation) {
        if (reservation.getId() == null) {
            reservation.setId(idCounter++);
        }
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }

    public void deleteById(Long id) {
        reservations.remove(id);
    }

    public List<Reservation> findByGuestId(Long guestId) {
        List<Reservation> result = new ArrayList<>();
        for (Reservation reservation : reservations.values()) {
            if (reservation.getGuest().getId().equals(guestId)) {
                result.add(reservation);
            }
        }
        return result;
    }
}