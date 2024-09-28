package com.cms.controller;

import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cms.entity.User;
import com.cms.model.UserModel;
import com.cms.service.UserService;



@Controller
public class UserDetailController {

	private UserService userService;

	public UserDetailController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/registration")
	public String getRegisterForm(@ModelAttribute("user")User users) {
		return "registration";
	}
	@PostMapping("/registration")
	public String handlingRegistration(@ModelAttribute("user")UserModel userModel){
		User user=userService.findUserByEmail(userModel.getEmail());
		if(Objects.isNull(user)) {
			if (userModel.getPassword().equals(userModel.getConfirmPassword())) {
				userService.createUserDetails(userModel.getUserDetails());
				return "redirect:/login?success";
			}
			else {
				return "redirect:/registration?passwordfailure";
			}
		}
		else {
			return "redirect:/registration?fail";
		}
		
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
	@GetMapping("/")
	public String home(Model model) {
		return "login";
	}
	
}
