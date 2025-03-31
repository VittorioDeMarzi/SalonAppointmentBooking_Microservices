package com.demadev.controller;

import com.demadev.model.ServiceOffering;
import com.demadev.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/service-offering")
@RequiredArgsConstructor
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getServiceOfferingsBySalonId(
            @PathVariable Long salonId,
            @RequestParam(required = false) Long categoryId) {

        Set<ServiceOffering> serviceOfferings = serviceOfferingService.getAllServiceBySalonId(salonId, categoryId);
        return ResponseEntity.ok(serviceOfferings);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceOffering> getServiceOfferingsById(
            @PathVariable Long serviceId) throws Exception {

        ServiceOffering serviceOffering = serviceOfferingService.getServiceById(serviceId);
        return ResponseEntity.ok(serviceOffering);
    }

    @GetMapping("/list/{serviceIds}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByIds(
            @PathVariable Set<Long> serviceIds) {

        Set<ServiceOffering> serviceOfferings = serviceOfferingService.getServicesByIds(serviceIds);
        return ResponseEntity.ok(serviceOfferings);
    }
}
