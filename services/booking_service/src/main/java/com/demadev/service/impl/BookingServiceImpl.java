package com.demadev.service.impl;

import com.demadev.domain.BookingStatus;
import com.demadev.dto.BookingRequest;
import com.demadev.dto.SalonDto;
import com.demadev.dto.ServiceDto;
import com.demadev.dto.UserDto;
import com.demadev.model.Booking;
import com.demadev.model.SalonReport;
import com.demadev.repository.BookingRepository;
import com.demadev.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingRequest request, UserDto user, SalonDto salon, Set<ServiceDto> serviceDtoSet) {
        return null;
    }

    @Override
    public List<Booking> getBookingsByCustomer(Long customerId) {
        return List.of();
    }

    @Override
    public List<Booking> getBookingsBySalon(Long salonId) {
        return List.of();
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return null;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) {
        return null;
    }

    @Override
    public List<Booking> getBookingByDate(LocalDateTime date, Long salonId) {
        return List.of();
    }

    @Override
    public SalonReport getSalonReportById(Long salonId) {
        return null;
    }
}
