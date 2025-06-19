package unimiskolc.java.hotel_booking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

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
    @ToString.Exclude
    private Guest guest;

    @ManyToOne
    @ToString.Exclude
    private Room room;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    @ManyToMany
    @ToString.Exclude
    private List<HotelService> hotelServices;
}