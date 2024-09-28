package com.cms.service;

import com.cms.entity.User;

public interface UserService {

	User createUserDetails(User users);
	
	User findUserByEmail(String email);
	
	User getUserByuserId(int userId);
	
}
