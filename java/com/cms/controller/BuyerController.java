package com.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cms.entity.CarDetail;
import com.cms.entity.User;
import com.cms.service.CarService;
import com.cms.service.UserService;

@Controller
public class BuyerController {

	@Autowired
	private CarService carService;

	@Autowired
	private UserService userService;
	
	@GetMapping("/buyer")
	public String buyerPage(Model model,@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findUserByEmail(userDetails.getUsername());
		int userId = user.getUserId();
		List<CarDetail> cars=carService.getAllCarDetails();

		model.addAttribute("userId", userId);
		model.addAttribute("cars", cars);
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("userEmail", user.getEmail());

		return "buyer";
	}
	@GetMapping("/buyer/cart")
	public String addToCart() {
		return "cart";
	}

}
