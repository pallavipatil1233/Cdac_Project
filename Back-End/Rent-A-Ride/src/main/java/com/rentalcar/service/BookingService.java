package com.rentalcar.service;

import java.time.LocalDate;
import java.util.List;

import com.rentalcar.pojos.Booking;

public interface BookingService {

	List<Booking> getAllBookings();
	
	
	Booking bookRide(Long customerId, Long carId, LocalDate startDate, LocalDate endDate) throws Exception;
	

}
