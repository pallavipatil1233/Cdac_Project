package com.rentalcar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rentalcar.pojos.Customer;
import com.rentalcar.pojos.Booking;
import com.rentalcar.service.CustomerService;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Endpoint to get all customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // Endpoint to get a customer by id
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        return customer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to create a new customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }
    

    // Endpoint to update an existing customer
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customer.setId(id);
        Customer updatedCustomer = customerService.updateCustomer(customer);
        return updatedCustomer != null 
                ? new ResponseEntity<>(updatedCustomer, HttpStatus.OK) 
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to delete a customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint for customer login
    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestBody Customer customer) {
        Optional<Customer> loggedInCustomer = customerService.login(customer.getEmail(), customer.getPassword());
        return loggedInCustomer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

    // Endpoint to book a car
    @PostMapping("/{id}/book-car")
    public ResponseEntity<Booking> bookCar(@PathVariable Long id, @RequestBody Booking booking) {
        Optional<Booking> bookedCar = customerService.bookCar(id, booking);
        return bookedCar.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
   

}
