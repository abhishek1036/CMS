package com.cms.model;

import java.util.List;

import com.cms.entity.CarDetail;
import com.cms.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	
	private int userId;
	private String userName;
	@Email(message="Please enter a valid emailId",regexp = "[a-z0-9. _%+-]+@[a-z0-9. -]+\\.[a-z]{2,}")
	@NotEmpty(message="Email cannot be empty")
	private String email;
	private String password;
	private String confirmPassword;
	private String phoneNumber;
	private String role;
	
	private List<CarDetail> carDetails;

	public User getUserDetails() {
		return new User(userId,userName,email,password,phoneNumber,role,carDetails);
	}
}
