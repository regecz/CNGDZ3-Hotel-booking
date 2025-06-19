package unimiskolc.java.hotel_booking.dto;

import lombok.Data;

@Data
public class GuestDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}