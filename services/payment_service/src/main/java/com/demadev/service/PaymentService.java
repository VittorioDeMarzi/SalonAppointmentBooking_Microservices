package com.demadev.service;

import com.demadev.domain.PaymentMethod;
import com.demadev.model.PaymentOrder;
import com.demadev.payload.dto.BookingDto;
import com.demadev.payload.dto.UserDto;
import com.demadev.payload.response.PaymentLinkResponse;
import com.stripe.model.PaymentLink;

import java.math.BigDecimal;

public interface PaymentService {

    PaymentLinkResponse createOder(UserDto user,
                                   BookingDto booking,
                                   PaymentMethod paymentMethod);

    PaymentOrder getPaymentOrderById(Long id);

    PaymentOrder getPaymentOrderByPaymentId(String paymentId);
    PaymentLink createStripePaymentLink(UserDto user,
                                        BigDecimal amount,
                                        Long orderId);

}
