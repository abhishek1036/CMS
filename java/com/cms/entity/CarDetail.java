package com.cms.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="car_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int carId;
	
	private String carName;
	
	private String info;
	
	private double price;
	
	
	private int count;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	

}
