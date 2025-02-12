package com.rentalcar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalcar.pojos.Booking;

public interface BookingDao extends JpaRepository<Booking, Long> {

}
