package com.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.entity.CarDetail;
import com.cms.entity.User;
import com.cms.repository.CarRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CarServiceImpl implements CarService{
	
	@Autowired
	private CarRepository carRepository;
	
	@Override
	public CarDetail createCarDetails(CarDetail carDetail) {
		return carRepository.save(carDetail);
	}

	@Override
	public List<CarDetail> getAllCarDetails() {
		return carRepository.findAll();
	}

	@Override
	public CarDetail getCarById(int carId) {
		return carRepository.findById(carId).orElse(null);
	}

	@Override
	public List<CarDetail> getAllCarDetailsByUserId(int userId) {
		return carRepository.findByUser(userId);
	}

	@Override
	public CarDetail checkCarDetailAlreadyExist(CarDetail carDetail, User user) {
		String carName=carDetail.getCarName();
		return carRepository.findByUserAndCarName(user, carName);
	}

	@Override
	public void deleteCar(User user, int carId) throws Exception {
		Optional<CarDetail> carOptional=carRepository.findById(carId);
		if(carOptional.isPresent()) {
			carRepository.deleteByCarId(carId);
		}
		else {
			throw new Exception("Failed to delete Car Details");
		}
		
	}

	@Override
	public void updateCarCount(int carId, int count) {
		CarDetail car=	carRepository.findById(carId)
				.orElseThrow(()->new EntityNotFoundException("Car not found with id"+carId));
		car.setCount(count);
		carRepository.save(car);
		
	}

	@Override
	public boolean checkCarExistsForUser(String carName, User user) {
		return carRepository.existsByUserAndCarName(user, carName);
	}

	@Override
	public void updateCarCountandPrice(int carId, double price, int count) {
		CarDetail car=carRepository.findById(carId)
				.orElseThrow(()->new EntityNotFoundException("Car not found with id"+carId));
		
		car.setCount(count);
		car.setPrice(price);
		carRepository.save(car);
	}

}
