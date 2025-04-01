package com.demadev.payload.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingRequest {

    private LocalDateTime startTime;
    private Set<Long> serviceIds;

}
