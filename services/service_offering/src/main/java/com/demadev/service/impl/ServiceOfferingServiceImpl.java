package com.demadev.service.impl;

import com.demadev.dto.CategoryDto;
import com.demadev.dto.SalonDto;
import com.demadev.dto.ServiceDto;
import com.demadev.model.ServiceOffering;
import com.demadev.repository.ServiceOfferingRepository;
import com.demadev.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        serviceOffering.setCategoryId(categoryDto.getId());
        serviceOffering.setSalonId(salonDto.getId());

        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId,
                                         ServiceOffering serviceOfferingDto) throws Exception {
        ServiceOffering serviceToUpdate = getServiceById(serviceId);
        serviceToUpdate.setName(serviceOfferingDto.getName());
        serviceToUpdate.setDescription(serviceOfferingDto.getDescription());
        serviceToUpdate.setPrice(serviceOfferingDto.getPrice());
        serviceToUpdate.setImage(serviceOfferingDto.getImage());
        serviceToUpdate.setDuration(serviceOfferingDto.getDuration());
        serviceToUpdate.setCategoryId(serviceOfferingDto.getCategoryId());
        serviceToUpdate.setSalonId(serviceOfferingDto.getSalonId());
        return serviceOfferingRepository.save(serviceToUpdate);
    }

    @Override
    public Set<ServiceOffering> getAllServiceBySalonId(Long salonId, Long categoryId) {
        Set<ServiceOffering> services = serviceOfferingRepository.findBySalonId(salonId);
        if (categoryId != null) {
            services = services.stream()
                   .filter(service -> service.getCategoryId().equals(categoryId))
                   .collect(Collectors.toSet());
        }
        return services;
    }

    @Override
    public Set<ServiceOffering> getServicesByIds(Set<Long> serviceIds) {
        List<ServiceOffering> services = serviceOfferingRepository.findAllById(serviceIds);
        return new HashSet<>(services);
    }

    @Override
    public ServiceOffering getServiceById(Long serviceId) throws Exception {
        ServiceOffering service = serviceOfferingRepository.findById(serviceId).orElse(null);
        if (service == null) {
            throw new Exception("Service not found with id " + serviceId);
        }
        return service;
    }
}
