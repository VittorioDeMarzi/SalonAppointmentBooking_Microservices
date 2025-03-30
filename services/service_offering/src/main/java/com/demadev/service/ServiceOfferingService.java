package com.demadev.service;

import com.demadev.dto.CategoryDto;
import com.demadev.dto.SalonDto;
import com.demadev.dto.ServiceDto;
import com.demadev.model.ServiceOffering;

import java.util.Set;

public interface ServiceOfferingService {

    ServiceOffering createService(
            SalonDto salonDto,
            ServiceDto serviceDto,
            CategoryDto categoryDto);

    ServiceOffering updateService(Long serviceId, ServiceOffering serviceOffering);

    Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long CategoryId);

    Set<ServiceOffering> getServicesByIds(Set<Long> serviceIds);
}
