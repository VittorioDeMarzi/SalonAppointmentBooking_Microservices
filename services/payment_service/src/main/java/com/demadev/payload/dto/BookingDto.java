package com.demadev.payload.dto;

import com.demadev.domain.BookingStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {

    private Long id;

    private Long salonId;

    private Long customerId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Set<Long> serviceIds;

    private BookingStatus status;

    private BigDecimal totalPrice;
}
