package com.rentalcar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentalcar.pojos.Admin;
import com.rentalcar.pojos.Booking;
import com.rentalcar.pojos.Car;
import com.rentalcar.service.AdminService;
import com.rentalcar.service.BookingService;
import com.rentalcar.service.CarService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CarService carService;

    @Autowired
    private BookingService bookingService;

    // Admin Registration
    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.registerAdmin(admin), HttpStatus.CREATED);
    }

    // Admin Login
    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestBody Admin admin) {
        Optional<Admin> loggedInAdmin = adminService.loginAdmin(admin.getEmail(), admin.getPassword());
        return loggedInAdmin.isPresent() 
                ? new ResponseEntity<>("Login Successful", HttpStatus.OK) 
                : new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/addCar")
    public ResponseEntity<?> addCar(@RequestBody Car car) {
        try {
            if (car == null || car.getBrand() == null || car.getModel() == null) {
                return new ResponseEntity<>("Car details are incomplete", HttpStatus.BAD_REQUEST);
            }
            Car savedCar = carService.addCar(car);
            return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding car: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateCar/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @RequestBody Car car) {
        try {
            Car updatedCar = adminService.updateCar(id, car);
            return new ResponseEntity<>(updatedCar, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating car: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Delete Car
    @DeleteMapping("/deleteCar/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        try {
            adminService.deleteCar(id);
            return new ResponseEntity<>("Car deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting car: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBookings() {
        try {
            List<Booking> bookings = bookingService.getAllBookings();
            if (bookings.isEmpty()) {
                return new ResponseEntity<>("No bookings found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching bookings: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
