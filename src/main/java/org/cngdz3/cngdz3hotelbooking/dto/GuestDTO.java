package org.cngdz3.cngdz3hotelbooking.dto;

import lombok.Data;

@Data
public class GuestDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private boolean isVip;
}