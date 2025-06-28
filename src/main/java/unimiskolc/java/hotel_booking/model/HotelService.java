package unimiskolc.java.hotel_booking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "services")
public class HotelService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private double price;

    @ManyToMany
    @ToString.Exclude
    private List<Booking> bookings;
}