package com.demadev.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SalonReport {

    private Long salonId;
    private String salonName;
    private BigDecimal totalEarning;
    private Integer totalBookings;
    private List<Booking> cancelledBookings;
    private BigDecimal totalRefund;
}
