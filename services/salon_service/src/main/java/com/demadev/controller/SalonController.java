package com.demadev.controller;

import com.demadev.mapper.SalonMapper;
import com.demadev.model.Salon;
import com.demadev.payload.dto.SalonDto;
import com.demadev.payload.dto.UserDto;
import com.demadev.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salons")
@RequiredArgsConstructor
public class SalonController {

    private final SalonService salonService;

    //    http://localhost:5002/api/v1/salons
    @PostMapping
    public ResponseEntity<SalonDto> createSalon(
            @RequestBody SalonDto req) {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        Salon salon = salonService.createSalon(req, userDto);
        SalonDto salonDto = SalonMapper.mapToDto(salon);
        return new ResponseEntity<>(salonDto, HttpStatus.CREATED);
    }

    //    http://localhost:5002/api/v1/salons/2
    @PutMapping("/{salonId}")
    public ResponseEntity<SalonDto> updateSalon(
            @RequestBody SalonDto req,
            @PathVariable("salonId") Long salonId) {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        Salon salon = salonService.updateSalon(req, userDto, salonId);
        SalonDto salonDto = SalonMapper.mapToDto(salon);
        return new ResponseEntity<>(salonDto, HttpStatus.OK);
    }

    //    http://localhost:5002/api/v1/salons
    @GetMapping
    public ResponseEntity<List<SalonDto>> getSalons() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        List<Salon> list = salonService.getAllSalons();
        List<SalonDto> listDto = list.stream().map(SalonMapper::mapToDto).toList();
        return new ResponseEntity<>(listDto, HttpStatus.OK);
    }





}
