package com.demadev.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ServiceDto {
    private Long id;

    private String name;

    private String description;

    private int price;

    private int duration;

    private Long salonId;

    private Long categoryId;

    private String image;
}
