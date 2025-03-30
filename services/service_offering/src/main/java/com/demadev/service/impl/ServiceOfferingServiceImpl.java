package com.demadev.service.impl;

import com.demadev.dto.CategoryDto;
import com.demadev.dto.SalonDto;
import com.demadev.dto.ServiceDto;
import com.demadev.model.ServiceOffering;
import com.demadev.repository.ServiceOfferingRepository;
import com.demadev.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createService(SalonDto salonDto,
                                         ServiceDto serviceDto,
                                         CategoryDto categoryDto) {
        ServiceOffering serviceOffering = new ServiceOffering();
        serviceOffering.setName(serviceDto.getName());
        serviceOffering.setDescription(serviceDto.getDescription());
        serviceOffering.setPrice(serviceDto.getPrice());
        serviceOffering.setImage(serviceDto.getImage());
        serviceOffering.setDuration(serviceDto.getDuration());
        serviceOffering.setCategoryId(serviceDto.getCategoryId());
        serviceOffering.setSalonId(serviceDto.getSalonId());

        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId,
                                         ServiceOffering serviceOffering) {
        return null;
    }

    @Override
    public Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long CategoryId) {
        return serviceOfferingRepository.findBySalonId(salonId);
    }

    @Override
    public Set<ServiceOffering> getServicesByIds(Set<Long> serviceIds) {
        return Set.of();
    }
}
