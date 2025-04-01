package com.demadev;

import com.demadev.dto.BookingDto;
import com.demadev.model.Booking;

import java.util.stream.Collectors;

public class BookingMapper {

    public static BookingDto mapToDto(Booking booking) {

        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(booking.getId());
        bookingDto.setSalonId(booking.getSalonId());
        bookingDto.setCustomerId(booking.getCustomerId());
        bookingDto.setStartTime(booking.getStartTime());
        bookingDto.setEndTime(booking.getEndTime());
        bookingDto.setServiceIds(booking.getServiceIds());
        bookingDto.setStatus(booking.getStatus());
        bookingDto.setTotalPrice(booking.getTotalPrice());

        return bookingDto;
    }
}
