package com.rentalcar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalcar.pojos.Car;

public interface CarDao extends JpaRepository<Car, Long> {

}
