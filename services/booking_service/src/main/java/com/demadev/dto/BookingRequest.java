package com.demadev.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class BookingRequest {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Set<Long> serviceIds;

}
