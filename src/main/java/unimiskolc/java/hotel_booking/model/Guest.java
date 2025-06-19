package unimiskolc.java.hotel_booking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "guests")
@ToString
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Booking> bookings;

}