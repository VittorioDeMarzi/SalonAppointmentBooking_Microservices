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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingRequest request,
                                 UserDto user,
                                 SalonDto salon,
                                 Set<ServiceDto> serviceDtoSet) throws Exception {

        int totalDuration = serviceDtoSet.stream()
                .mapToInt(ServiceDto::getDuration).sum();

        LocalDateTime bookingStartTime = request.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);

        Boolean isSlotAvailable = isTimeSlotAvailable(salon, bookingEndTime, bookingEndTime);

        int totalPrice = serviceDtoSet.stream()
                .mapToInt(ServiceDto::getPrice).sum();

        Set<Long> idList = serviceDtoSet.stream()
                .map(ServiceDto::getId)
                .collect(Collectors.toSet());

        Booking newBooking = new Booking();
        newBooking.setCustomerId(user.getId());
        newBooking.setSalonId(salon.getId());
        newBooking.setServiceIds(idList);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setStartTime(bookingStartTime);
        newBooking.setEndTime(bookingEndTime);
        newBooking.setTotalPrice(totalPrice);

        return bookingRepository.save(newBooking);
    }

    public Boolean isTimeSlotAvailable(SalonDto salonDto,
                                       LocalDateTime bookingStartTime,
                                       LocalDateTime bookingEndTime) throws Exception {

        LocalDateTime salonOpenTime = salonDto.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonCloseTime = salonDto.getCloseTime().atDate(bookingStartTime.toLocalDate());

        // Check if the booking time is within the salon's operating hours)
        if (bookingStartTime.isBefore(salonOpenTime) || bookingEndTime.isBefore(salonCloseTime))
            throw new Exception("Booking time must be within salon's working hours");

        // Check if there are any existing bookings within the booking time
        List<Booking> existingBookings = bookingRepository.findBookingBxSalonId(salonDto.getId());
        for (Booking booking : existingBookings) {
            LocalDateTime existingBookingStartTime = booking.getStartTime();
            LocalDateTime existingBookingEndTime = booking.getEndTime();

            if (booking.getStartTime().isBefore(bookingEndTime) &&
                    booking.getEndTime().isAfter(bookingStartTime))
                throw new Exception("Slot not available, choose a different time");

            if(booking.getStartTime().isEqual(existingBookingStartTime) ||
                booking.getEndTime().isEqual(existingBookingEndTime))
                throw new Exception("Slot not available, choose a different time");
        }
        return null;
    }


    @Override
    public List<Booking> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findBookingBxCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingsBySalon(Long salonId) {
        return bookingRepository.findBookingBxSalonId(salonId);
    }

    @Override
    public Booking getBookingById(Long bookingId) throws Exception {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            throw new Exception("Booking not found with id " + bookingId);
        }
        return booking;
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
