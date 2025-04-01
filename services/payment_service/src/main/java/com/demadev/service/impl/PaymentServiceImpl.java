package com.demadev.service.impl;

import com.demadev.domain.PaymentMethod;
import com.demadev.domain.PaymentOrderStatus;
import com.demadev.model.PaymentOrder;
import com.demadev.payload.dto.BookingDto;
import com.demadev.payload.dto.UserDto;
import com.demadev.payload.response.PaymentLinkResponse;
import com.demadev.repository.PaymentRepository;
import com.demadev.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentLink;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;


    @Override
    public PaymentLinkResponse createOder(UserDto user,
                                          BookingDto booking,
                                          PaymentMethod paymentMethod) throws StripeException {

        BigDecimal amount = booking.getTotalPrice();
        PaymentOrder order = new PaymentOrder();
        order.setAmount(amount);
        order.setPaymentMethod(paymentMethod);
        order.setBookingId(booking.getId());
        order.setSalonId(booking.getSalonId());
        PaymentOrder savedOrder = paymentRepository.save(order);

        String paymentUrl = createStripePaymentLink(user, amount, order.getId());
        return null;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) throws Exception {
        PaymentOrder paymentOrder = paymentRepository.findById(id).orElse(null);
        if(paymentOrder==null) {
            throw new Exception("Payment order not found for id " + id);
        }
        return paymentOrder;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
        return null;
    }

    @Override
    public String createStripePaymentLink(UserDto user, BigDecimal amount, Long orderId) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        Long amountLongInCents = amount.multiply(new BigDecimal("100")).longValue();

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("https://localhost:3000/payment/payment-success/"+orderId)
                .setCancelUrl("https://localhost:3000/payment/payment-cancel")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams
                                .LineItem
                                .PriceData
                                .builder()
                                .setCurrency("EUR")
                                .setUnitAmount(amountLongInCents)
                                .setProductData(SessionCreateParams
                                        .LineItem
                                        .PriceData
                                        .ProductData
                                        .builder().setName("Salon Appointment Booking").build())
                                .build())
                        .build())
                .build();
        Session session = Session.create(params);
        return session.getUrl();

    }

    @Override
    public Boolean proceedPayment(PaymentOrder paymentOrder,
                                  String paymentId,
                                  String paymentLinkId) {

        if(paymentOrder.getPaymentStatus().equals(PaymentOrderStatus.PENDING)) {
            paymentOrder.setPaymentStatus(PaymentOrderStatus.SUCCESS);
            paymentRepository.save(paymentOrder);
            return true;
        }
        return false;
    }
}
