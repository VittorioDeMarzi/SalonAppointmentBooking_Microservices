package com.demadev.model;

import com.demadev.domain.PaymentMethod;
import com.demadev.domain.PaymentOrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private PaymentOrderStatus paymentStatus = PaymentOrderStatus.PENDING;

    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    private String paymentLinkId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long bookingId;

    @Column(nullable = false)
    private Long salonId;
}
