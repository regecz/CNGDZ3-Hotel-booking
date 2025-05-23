package org.cngdz3.cngdz3hotelbooking.dto;

import lombok.Data;

@Data
public class HotelDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
    private int starRating;
}