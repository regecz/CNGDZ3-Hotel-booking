package org.cngdz3.cngdz3hotelbooking.service;

import org.cngdz3.cngdz3hotelbooking.model.Guest;
import org.cngdz3.cngdz3hotelbooking.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Guest getGuestById(Long id) {
        return guestRepository.findById(id);
    }

    public void addGuest(Guest guest) {
        guestRepository.save(guest);
    }

    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }
}