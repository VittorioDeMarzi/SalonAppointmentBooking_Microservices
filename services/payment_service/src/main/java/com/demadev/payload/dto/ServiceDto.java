package com.demadev.payload.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceDto {
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private int duration;

    private Long salonId;

    private Long categoryId;

    private String image;
}
