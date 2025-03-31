package com.demadev.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingSlotDto {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
