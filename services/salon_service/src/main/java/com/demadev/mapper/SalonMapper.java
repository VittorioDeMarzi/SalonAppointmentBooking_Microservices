package com.demadev.mapper;

import com.demadev.model.Salon;
import com.demadev.payload.dto.SalonDto;

public class SalonMapper {

    public static SalonDto mapToDto(Salon salon) {
        SalonDto salonDto = new SalonDto();
        salonDto.setId(salon.getId());
        salonDto.setName(salon.getName());
        salonDto.setAddress(salon.getAddress());
        salonDto.setEmail(salon.getEmail());
        salonDto.setPhoneNumber(salon.getPhoneNumber());
        salonDto.setCity(salon.getCity());
        salonDto.setImages(salon.getImages());
        salonDto.setOpenTime(salon.getOpenTime());
        salonDto.setCloseTime(salon.getCloseTime());
        return salonDto;
    }

}
