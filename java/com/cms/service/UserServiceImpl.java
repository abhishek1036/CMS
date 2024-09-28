package com.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cms.entity.User;
import com.cms.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User createUserDetails(User users) {
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		return userRepository.save(users);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User getUserByuserId(int userId) {
		return userRepository.getUserByuserId(userId);
	}

}
