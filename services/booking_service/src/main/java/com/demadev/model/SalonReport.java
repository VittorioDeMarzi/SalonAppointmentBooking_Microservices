package com.demadev.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalonReport {

    private Long salonId;
    private String salonName;
    private Double totalEarning;
    private Integer totalBookings;
    private Integer cancelledBookings;
    private BigDecimal totalRefund;
}
