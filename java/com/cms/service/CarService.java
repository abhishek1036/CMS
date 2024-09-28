package com.cms.service;

import java.util.List;

import com.cms.entity.CarDetail;
import com.cms.entity.User;

public interface CarService {

	CarDetail createCarDetails(CarDetail carDetail);
	
	List<CarDetail> getAllCarDetails();
	
	CarDetail getCarById(int carId);
	
	List<CarDetail> getAllCarDetailsByUserId(int userId);
	
	CarDetail checkCarDetailAlreadyExist(CarDetail carDetail, User user);
	
	void deleteCar(User user, int carId) throws Exception;
	
	void updateCarCount(int carId, int count);
	
	void updateCarCountandPrice(int carId, double price, int count);
	
	boolean checkCarExistsForUser(String carName, User user);
}
