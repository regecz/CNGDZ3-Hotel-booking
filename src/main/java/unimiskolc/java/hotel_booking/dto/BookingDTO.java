package unimiskolc.java.hotel_booking.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class BookingDTO {
    private Long id;
    private Long roomId;
    private Long guestId;
    private List<Long> serviceIds;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}