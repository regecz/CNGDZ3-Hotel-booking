package org.cngdz3.cngdz3hotelbooking.repository;

import org.cngdz3.cngdz3hotelbooking.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HotelRepository {
    private final Map<Long, Hotel> hotels = new HashMap<>();
    private long idCounter = 1;

    public List<Hotel> findAll() {
        return new ArrayList<>(hotels.values());
    }

    public Hotel findById(Long id) {
        return hotels.get(id);
    }

    public Hotel save(Hotel hotel) {
        if (hotel.getId() == null) {
            hotel.setId(idCounter++);
        }
        hotels.put(hotel.getId(), hotel);
        return hotel;
    }

    public void deleteById(Long id) {
        hotels.remove(id);
    }

    public List<Hotel> findByStarRating(int starRating) {
        List<Hotel> result = new ArrayList<>();
        for (Hotel hotel : hotels.values()) {
            if (hotel.getStarRating() == starRating) {
                result.add(hotel);
            }
        }
        return result;
    }
}