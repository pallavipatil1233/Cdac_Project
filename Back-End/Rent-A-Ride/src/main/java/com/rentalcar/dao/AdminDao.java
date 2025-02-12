package com.rentalcar.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalcar.pojos.Admin;


public interface AdminDao extends JpaRepository<Admin, Long> {
	
	Optional<Admin> findByEmail(String email);

}
