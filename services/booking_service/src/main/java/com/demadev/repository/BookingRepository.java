package com.demadev.repository;

import com.demadev.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findBookingBxCustomerId(Long customerId);
    List<Booking> findBookingBxSalonId(Long salonId);
}
