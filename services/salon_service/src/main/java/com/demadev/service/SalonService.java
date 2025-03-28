package com.demadev.service;

import com.demadev.model.Salon;
import com.demadev.payload.dto.SalonDto;
import com.demadev.payload.dto.UserDto;

import java.util.List;

public interface SalonService {
    Salon createSalon(SalonDto salon, UserDto user);

    Salon updateSalon(SalonDto salon, UserDto user, Long salonId);

    List<Salon> getAllSalons();

    Salon getSalonById(Long salonId);

    void deleteSalonById(Long salonId);

    Salon getSalonsByOwnerId(Long userId);

    List<Salon> getSalonsByCity(String city);

}
