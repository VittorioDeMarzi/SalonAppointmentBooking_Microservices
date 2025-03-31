package com.demadev.controller;

import com.demadev.dto.CategoryDto;
import com.demadev.dto.SalonDto;
import com.demadev.dto.ServiceDto;
import com.demadev.model.ServiceOffering;
import com.demadev.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/service-offering/salon-owner")
public class ServiceOfferingSalonOwnerController {

    private final ServiceOfferingService serviceOfferingService;

    @PostMapping
    public ResponseEntity<ServiceOffering> createService(
            @RequestBody ServiceDto serviceDto) {

        SalonDto salonDto = new SalonDto();
        salonDto.setId(1L);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(serviceDto.getCategoryId());

        ServiceOffering serviceOffering = serviceOfferingService.createService(salonDto, serviceDto, categoryDto);

        return ResponseEntity.ok(serviceOffering);
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<ServiceOffering> updateService(
            @RequestBody ServiceOffering serviceOffering,
            @PathVariable Long serviceId) throws Exception {

        
        ServiceOffering updatedServiceOffering = serviceOfferingService.updateService(serviceId, serviceOffering);

        return ResponseEntity.ok(updatedServiceOffering);
    }
}
