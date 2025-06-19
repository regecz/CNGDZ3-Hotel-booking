package unimiskolc.java.hotel_booking.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unimiskolc.java.hotel_booking.exception.GuestNotFoundException;
import unimiskolc.java.hotel_booking.model.Guest;
import unimiskolc.java.hotel_booking.repository.GuestRepository;

import java.util.List;

@Service
@Transactional
public class GuestService {
    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public void addGuest(Guest guest) {
        guest.setId(null);
        guestRepository.save(guest);
    }

    public void updateGuest(Guest guest) {
        if (guest.getId() == null) {
            throw new IllegalArgumentException("Guest ID cannot be null for update");
        }
        if (!guestRepository.existsById(guest.getId())) {
            throw new IllegalArgumentException("Guest not found with ID: " + guest.getId());
        }
        guestRepository.save(guest);
    }

    public void deleteGuest(Long id) {
        guestRepository.findById(id)
                .ifPresent(guestRepository::delete);
    }

    public Guest findGuestById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new GuestNotFoundException("Guest not found with ID: " + id));
    }

    public List<Guest> listGuests() {
        return guestRepository.findAll();
    }
}