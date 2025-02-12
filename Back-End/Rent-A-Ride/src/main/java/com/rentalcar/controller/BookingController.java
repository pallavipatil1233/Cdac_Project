package com.rentalcar.controller;


import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentalcar.pojos.Booking;
import com.rentalcar.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin("*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

//    @PostMapping("/book")
//    public ResponseEntity<?> bookRide(@RequestBody Map<String, String> request) {
//        try {
//            Long customerId = Long.parseLong(request.get("customerId"));
//            Long carId = Long.parseLong(request.get("carId"));
//            LocalDate startDate = LocalDate.parse(request.get("startDate"));
//            LocalDate endDate = LocalDate.parse(request.get("endDate"));
//
//            Booking booking = bookingService.bookRide(customerId, carId, startDate, endDate);
//            return ResponseEntity.ok(booking);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
//       
//        }
//    }
    
    @PostMapping("/book")
    public ResponseEntity<?> bookRide(@RequestBody Map<String, String> request) {
        try {
            String customerIdStr = request.get("customerId");
            String carIdStr = request.get("carId");

            if (customerIdStr == null || customerIdStr.equals("undefined") || carIdStr == null) {
                return ResponseEntity.badRequest().body("Invalid customerId or carId");
            }

            Long customerId = Long.parseLong(customerIdStr);
            Long carId = Long.parseLong(carIdStr);
            LocalDate startDate = LocalDate.parse(request.get("startDate"));
            LocalDate endDate = LocalDate.parse(request.get("endDate"));

            Booking booking = bookingService.bookRide(customerId, carId, startDate, endDate);
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
