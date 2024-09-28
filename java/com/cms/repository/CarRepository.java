package com.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cms.entity.CarDetail;
import com.cms.entity.User;

import jakarta.transaction.Transactional;

public interface CarRepository extends JpaRepository<CarDetail, Integer>{
	
	CarDetail findByUserAndCarName(User user, String carName);
	
	@Query(value = "SELECT c FROM CarDetail c WHERE c.user.userId=?1")
	List<CarDetail> findByUser(int userId);
	
	boolean existsByUserAndCarName(User user, String carName);

	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM CarDetail c WHERE c.carId = :carId")
	void deleteByCarId(int carId);
}
