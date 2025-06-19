package unimiskolc.java.hotel_booking.dto;

import lombok.Data;

@Data
public class RoomDTO {
    private Long id;
    private String roomNumber;
    private int capacity;
    private double pricePerNight;
}