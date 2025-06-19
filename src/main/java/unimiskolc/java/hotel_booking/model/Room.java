package unimiskolc.java.hotel_booking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "rooms")
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String roomNumber;
    private int capacity;
    private double pricePerNight;

    @OneToMany(mappedBy = "room")
    @ToString.Exclude
    private List<Booking> bookings;
}