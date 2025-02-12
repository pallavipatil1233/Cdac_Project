package com.rentalcar.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalcar.pojos.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

	Optional<Customer> findByEmailAndPassword(String email, String password);
	

}
