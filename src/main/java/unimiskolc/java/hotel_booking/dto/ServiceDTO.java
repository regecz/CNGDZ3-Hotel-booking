package unimiskolc.java.hotel_booking.dto;

import lombok.Data;

@Data
public class ServiceDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
}