package com.rentalcar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentalcar.pojos.Car;
import com.rentalcar.service.CarService;

@RestController
@RequestMapping("/cars")
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {

    @Autowired
    private CarService carService;

    // API to get all available cars
    @GetMapping("/all")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    // API for admin to add a car
    @PostMapping("/add")
    public String addCar(@RequestBody Car car) {
        carService.addCar(car);
        return "Car added successfully!";
    }
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> car = carService.findById(id);
        return car.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }
    // API to book a car (Customer)
    @PostMapping("/book/{carId}/{customerId}")
    public String bookCar(@PathVariable Long carId, @PathVariable Long customerId) {
        return carService.bookCar(carId, customerId);
    }
}
