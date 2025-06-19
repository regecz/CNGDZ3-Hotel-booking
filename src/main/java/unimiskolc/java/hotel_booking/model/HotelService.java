package unimiskolc.java.hotel_booking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "services")
@ToString(exclude = "bookings")
public class HotelService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private double price;

    @ManyToMany
    private List<Booking> bookings;
}