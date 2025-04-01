package com.demadev.service.impl;

import com.demadev.domain.PaymentMethod;
import com.demadev.model.PaymentOrder;
import com.demadev.payload.dto.BookingDto;
import com.demadev.payload.dto.UserDto;
import com.demadev.payload.response.PaymentLinkResponse;
import com.demadev.repository.PaymentRepository;
import com.demadev.service.PaymentService;
import com.stripe.model.PaymentLink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentLinkResponse createOder(UserDto user, BookingDto booking, PaymentMethod paymentMethod) {
        return null;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) {
        return null;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
        return null;
    }

    @Override
    public PaymentLink createStripePaymentLink(UserDto user, BigDecimal amount, Long orderId) {
        return null;
    }
}
