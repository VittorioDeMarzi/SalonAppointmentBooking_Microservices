package com.demadev.service;

import com.demadev.domain.BookingStatus;
import com.demadev.dto.BookingRequest;
import com.demadev.dto.SalonDto;
import com.demadev.dto.ServiceDto;
import com.demadev.dto.UserDto;
import com.demadev.model.Booking;
import com.demadev.model.SalonReport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


public interface BookingService {

    Booking createBooking(BookingRequest request, UserDto user, SalonDto salon, Set<ServiceDto> serviceDtoSet) throws Exception;

    List<Booking> getBookingsByCustomer(Long customerId);
    List<Booking> getBookingsBySalon(Long salonId);
    Booking getBookingById(Long bookingId) throws Exception;
    Booking updateBooking(Long bookingId, BookingStatus status) throws Exception;
    List<Booking> getBookingByDate(LocalDate date, Long salonId);
    SalonReport getSalonReportById(Long salonId);

}
