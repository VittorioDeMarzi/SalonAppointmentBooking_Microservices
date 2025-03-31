package com.demadev.model;

import lombok.Data;

@Data
public class SalonReport {

    private Long salonId;
    private String salonName;
    private Double totalEarning;
    private Integer totalBookings;
    private Integer cancelledBookings;
    private Double totalRefund;
}
