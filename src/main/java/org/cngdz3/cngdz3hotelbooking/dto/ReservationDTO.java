package org.cngdz3.cngdz3hotelbooking.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDTO {
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double price;
    private RoomDTO room;
    private GuestDTO guest;
}