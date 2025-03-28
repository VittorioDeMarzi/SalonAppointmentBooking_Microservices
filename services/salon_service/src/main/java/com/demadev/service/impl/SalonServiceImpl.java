package com.demadev.service.impl;

import com.demadev.model.Salon;
import com.demadev.payload.dto.SalonDto;
import com.demadev.payload.dto.UserDto;
import com.demadev.repository.SalonRepository;
import com.demadev.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    @Override
    public Salon createSalon(SalonDto req, UserDto user) {
        Salon salon = new Salon();
        salon.setName(req.getName());
        salon.setAddress(req.getAddress());
        salon.setEmail(req.getEmail());
        salon.setPhoneNumber(req.getPhoneNumber());
        salon.setCity(req.getCity());
        salon.setOwnerId(user.getId());
        salon.setImages(req.getImages());
        salon.setOpenTime(req.getOpenTime());
        salon.setCloseTime(req.getCloseTime());
        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDto salon, UserDto user, Long salonId) {

        Salon existingSalon = salonRepository.findById(salonId).orElse(null);
        if(existingSalon == null) {
            throw new RuntimeException("Salon not found with id: " + salonId);
        }
        if(salon.getOwnerId() != user.getId()) {
            throw new RuntimeException("You are not authorized to update this salon");
        }
        existingSalon.setName(salon.getName());
        existingSalon.setAddress(salon.getAddress());
        existingSalon.setEmail(salon.getEmail());
        existingSalon.setPhoneNumber(salon.getPhoneNumber());
        existingSalon.setCity(salon.getCity());
        existingSalon.setOwnerId(user.getId());
        existingSalon.setImages(salon.getImages());
        existingSalon.setOpenTime(salon.getOpenTime());
        existingSalon.setCloseTime(salon.getCloseTime());
        return salonRepository.save(existingSalon);
    }

    @Override
    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) {
        Salon salon = salonRepository.findById(salonId).orElse(null);
        if(salon == null) {
            throw new RuntimeException("Salon not found with id: " + salonId);
        }
        return salon;
    }

    @Override
    public void deleteSalonById(Long salonId) {
        Salon salon = salonRepository.findById(salonId).orElse(null);
        if(salon == null) {
            throw new RuntimeException("Salon not found with id: " + salonId);
        }
        salonRepository.delete(salon);
    }

    @Override
    public Salon getSalonsByOwnerId(Long userId) {
        return salonRepository.findByOwnerId(userId);
    }

    @Override
    public List<Salon> getSalonsByCity(String city) {
        return salonRepository.searchSalon(city);
    }
}
