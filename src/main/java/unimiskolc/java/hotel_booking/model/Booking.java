package unimiskolc.java.hotel_booking.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Guest guest;

    @ManyToOne
    private Room room;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @ManyToMany
    private List<HotelService> hotelServices;
}