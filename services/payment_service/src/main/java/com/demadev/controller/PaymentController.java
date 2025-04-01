package com.demadev.controller;

import com.demadev.domain.PaymentMethod;
import com.demadev.model.PaymentOrder;
import com.demadev.payload.dto.BookingDto;
import com.demadev.payload.dto.UserDto;
import com.demadev.payload.response.PaymentLinkResponse;
import com.demadev.service.PaymentService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLinkResponse(
            @RequestBody BookingDto booking,
            @RequestParam PaymentMethod paymentMethod) throws StripeException {

        UserDto user = new UserDto();
        user.setId(1L);
        user.setFullName("Test User");
        user.setEmail("test@example.com");

        PaymentLinkResponse paymentLinkResponse = paymentService.createOder(user, booking, paymentMethod);
        return ResponseEntity.ok(paymentLinkResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentOrder> getPaymentOrderById(
            @PathVariable Long id) throws Exception {
        PaymentOrder paymentOrder = paymentService.getPaymentOrderById(id);
        return ResponseEntity.ok(paymentOrder);
    }

    @PatchMapping("/proceed")
    public ResponseEntity<Boolean> proceedPaymentOrder(
            @RequestParam String paymentId,
            @RequestParam String paymentLinkId) throws Exception {
        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkId);

        Boolean res = paymentService.proceedPayment(paymentOrder, paymentId, paymentLinkId);
        return ResponseEntity.ok(res);
    }

}
