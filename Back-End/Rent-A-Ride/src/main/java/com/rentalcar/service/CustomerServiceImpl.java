package com.rentalcar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentalcar.dao.BookingDao;
import com.rentalcar.dao.CustomerDao;
import com.rentalcar.pojos.Booking;
import com.rentalcar.pojos.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;
   
    @Autowired
    private BookingDao bookingDao;
    

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerDao.findById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if (customerDao.existsById(customer.getId())) {
            return customerDao.save(customer);
        }
        return null; // Or throw exception if customer doesn't exist
    }

    @Override
    public void deleteCustomer(Long id) {
        if (customerDao.existsById(id)) {
            customerDao.deleteById(id);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.findAll();
    }
    
    @Override
    public Optional<Customer> login(String email, String password) {
        return customerDao.findByEmailAndPassword(email, password);
    }

    @Override
    public Optional<Booking> bookCar(Long customerId, Booking booking) {
        // Check if customer exists
        Optional<Customer> customer = customerDao.findById(customerId);
        if (customer.isPresent()) {
            booking.setCustomer(customer.get());
            // Save the booking
            return Optional.of(bookingDao.save(booking));
        }
        return Optional.empty();
    }
}
