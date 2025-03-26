package com.demadev.payload.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class SalonDto {
    private Long id;

    private String name;

    private List<String> images;

    private String address;

    private String phoneNumber;

    private String email;

    private String city;

    private Long ownerId;

    private LocalTime openTime;

    private LocalTime closeTime;
}
