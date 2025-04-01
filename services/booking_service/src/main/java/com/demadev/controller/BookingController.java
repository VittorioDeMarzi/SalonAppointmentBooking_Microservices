package com.demadev.controller;

import com.demadev.BookingMapper;
import com.demadev.domain.BookingStatus;
import com.demadev.dto.*;
import com.demadev.model.Booking;
import com.demadev.model.SalonReport;
import com.demadev.service.BookingService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestBody BookingRequest request,
            @RequestParam Long salonId) throws Exception {

        UserDto user = new UserDto();
        user.setId(1L);

        SalonDto salon = new SalonDto();
        salon.setId(salonId);
        salon.setOpenTime(LocalTime.of(9, 0, 0));
        salon.setCloseTime(LocalTime.of(22, 0, 0));

        Set<ServiceDto> serviceDtos = new HashSet<>();

        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setId(1L);
        serviceDto.setPrice(new BigDecimal(100));
        serviceDto.setDuration(45);
        serviceDto.setName("Haircut for men");

        serviceDtos.add(serviceDto);

        Booking newBooking = bookingService.createBooking(request, user, salon, serviceDtos);

        return ResponseEntity.ok(newBooking);
    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDto>> getBookingsByCustomer() {

        List<Booking> bookings = bookingService.getBookingsByCustomer(1L);
        return ResponseEntity.ok(getBookingDtos(bookings));
    }

    private Set<BookingDto> getBookingDtos (List<Booking> bookings) {
        return bookings.stream()
               .map(BookingMapper::mapToDto)
               .collect(Collectors.toSet());
    }

    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDto>> getBookingsBySalon() {

        List<Booking> bookings = bookingService.getBookingsBySalon(1L);
        return ResponseEntity.ok(getBookingDtos(bookings));
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDto> getBookingsById(
            @PathVariable Long bookingId) throws Exception {

        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(BookingMapper.mapToDto(booking));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDto> updateBookingsStatus(
            @PathVariable Long bookingId,
            @RequestParam BookingStatus status) throws Exception {

        Booking booking = bookingService.updateBooking(bookingId, status);

        return ResponseEntity.ok(BookingMapper.mapToDto(booking));
    }

    @GetMapping("/slot/salon/{salonId}")
    public ResponseEntity<List<BookingSlotDto>> getBookedSlot(
            @PathVariable Long salonId,
            @RequestParam(required = false) LocalDate date) {
        {

            List<Booking> bookings = bookingService.getBookingByDate(date, salonId);
            List<BookingSlotDto> slots = bookings.stream()
                    .map(booking -> {
                        BookingSlotDto slot = new BookingSlotDto();
                        slot.setStartTime(booking.getStartTime());
                        slot.setEndTime(booking.getEndTime());
                        return slot;
                    })
                    .toList();
            return ResponseEntity.ok(slots);
        }
    }

    @GetMapping("/slot/salon/report")
    public ResponseEntity<SalonReport> getSalonReport() {
        {
            SalonReport salonReport = bookingService.getSalonReportById(1L);
            return ResponseEntity.ok(salonReport);
        }
    }
}
