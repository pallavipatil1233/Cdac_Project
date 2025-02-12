package com.rentalcar.service;

import java.util.List;
import java.util.Optional;

import com.rentalcar.pojos.Booking;
import com.rentalcar.pojos.Customer;

public interface CustomerService {
	
	Optional<Customer> getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(Long id);
    List<Customer> getAllCustomers();
    // Add the login method
    Optional<Customer> login(String email, String password);

    // Add the book car method
    Optional<Booking> bookCar(Long customerId, Booking booking);

}
