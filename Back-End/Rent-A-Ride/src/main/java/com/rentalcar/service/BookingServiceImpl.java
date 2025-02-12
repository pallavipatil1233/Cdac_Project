package com.rentalcar.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentalcar.dao.BookingDao;
import com.rentalcar.dao.CarDao;
import com.rentalcar.dao.CustomerDao;
import com.rentalcar.pojos.Booking;
import com.rentalcar.pojos.Booking.BookingStatus;
import com.rentalcar.pojos.Car;
import com.rentalcar.pojos.Customer;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CarDao carDao;

    public Booking bookRide(Long customerId, Long carId, LocalDate startDate, LocalDate endDate) throws Exception {
        // Fetch customer and car from the database
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new Exception("Customer with ID " + customerId + " not found"));

        Car car = carDao.findById(carId)
                .orElseThrow(() -> new Exception("Car with ID " + carId + " not found"));

        // Create a new booking
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setCar(car);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setStatus(BookingStatus.CONFIRMED); // Mark as confirmed

        // Save the booking
        return bookingDao.save(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingDao.findAll(); // Fixed the missing braces
    }
}
