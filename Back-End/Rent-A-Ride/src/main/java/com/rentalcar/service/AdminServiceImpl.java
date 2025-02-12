package com.rentalcar.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalcar.dao.AdminDao;
import com.rentalcar.dao.CarDao;
import com.rentalcar.pojos.Admin;
import com.rentalcar.pojos.Car;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;
    
    @Autowired
    private CarService carService;
    @Override
    public Admin registerAdmin(Admin admin) {
        return adminDao.save(admin);
    }

    @Override
    public Optional<Admin> loginAdmin(String email, String password) {
        return adminDao.findByEmail(email)
                .filter(admin -> admin.getPassword().equals(password));
    }
    @Override
    public Car updateCar(Long id, Car car) {
        return carService.updateCar(id, car);
    }

    @Override
    public void deleteCar(Long id) {
    	carService.deleteCar(id);
    }
}
