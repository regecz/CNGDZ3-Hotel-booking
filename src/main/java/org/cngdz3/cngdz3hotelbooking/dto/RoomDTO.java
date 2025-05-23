package org.cngdz3.cngdz3hotelbooking.dto;

import lombok.Data;

@Data
public class RoomDTO {
    private Long id;
    private String type;
    private int capacity;
    private double pricePerNight;
}