package com.rentalcar.service;

import java.util.Optional;

import com.rentalcar.pojos.Admin;
import com.rentalcar.pojos.Car;

public interface AdminService {

	Admin registerAdmin(Admin admin);
    Optional<Admin> loginAdmin(String email, String password);
    
    Car updateCar(Long id, Car car);
    void deleteCar(Long id);
    
}
