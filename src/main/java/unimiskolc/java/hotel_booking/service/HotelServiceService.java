package unimiskolc.java.hotel_booking.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unimiskolc.java.hotel_booking.model.HotelService;
import unimiskolc.java.hotel_booking.repository.HotelServiceRepository;

import java.util.List;

@Service
@Transactional
public class HotelServiceService {
    private final HotelServiceRepository hotelServiceRepository;

    @Autowired
    public HotelServiceService(HotelServiceRepository hotelServiceRepository) {
        this.hotelServiceRepository = hotelServiceRepository;
    }

    public void addService(HotelService hotelService) {
        hotelService.setId(null);
        hotelServiceRepository.save(hotelService);
    }

    public void updateService(HotelService hotelService) {
        if (hotelService.getId() == null) {
            throw new IllegalArgumentException("Service ID cannot be null for update");
        }
        if (!hotelServiceRepository.existsById(hotelService.getId())) {
            throw new IllegalArgumentException("Service not found with ID: " + hotelService.getId());
        }
        hotelServiceRepository.save(hotelService);
    }

    public void deleteService(Long id) {
        hotelServiceRepository.findById(id)
                .ifPresent(hotelServiceRepository::delete);
    }

    public HotelService findServiceById(Long id) {
        return hotelServiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service not found with ID: " + id));
    }

    public List<HotelService> listServices() {
        return hotelServiceRepository.findAll();
    }
}