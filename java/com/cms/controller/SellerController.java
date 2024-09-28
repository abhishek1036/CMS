package com.cms.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cms.entity.CarDetail;
import com.cms.entity.User;
import com.cms.service.CarService;
import com.cms.service.UserService;


@Controller
public class SellerController {
	
	@Autowired
	private CarService carService;

	@Autowired
	private UserService userService;
	
	@GetMapping("/seller")
	public String sellerPage(Model model,@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findUserByEmail(userDetails.getUsername());
		int sellerId = user.getUserId();

		List<CarDetail> cars=carService.getAllCarDetailsByUserId(sellerId);

		model.addAttribute("userName", user.getUserName());
		model.addAttribute("cars", cars);
		model.addAttribute("sellerId", sellerId);
		return "seller";
	}
	
	@GetMapping("/seller/addnew")
	public String getRegisterForm() {
		return "selleradd";
	}
	
	@PostMapping("/seller/addnew")
	public String handleNewCarDetails(@ModelAttribute("car") CarDetail carDetails,@AuthenticationPrincipal UserDetails userDetails) {
			User user = userService.findUserByEmail(userDetails.getUsername());
			carDetails.setUser(user);
			CarDetail existingCar = carService.checkCarDetailAlreadyExist(carDetails, user);
			if (Objects.isNull(existingCar)) {
				carService.createCarDetails(carDetails);

				return "redirect:/seller?cardetailsadded";
			} else {
				return "redirect:/seller?Alreadyexists";
			}
		
	}
	
	@GetMapping("/seller/delete")
	public String delete(@RequestParam("id") Integer id, @AuthenticationPrincipal UserDetails userDetails) throws Exception {
			User user = userService.findUserByEmail(userDetails.getUsername());
			carService.deleteCar(user, id);
		return "redirect:/seller?cardetailsdeleted";
	}

	@PostMapping("/seller/update")
	public String changeCarCountandPrice(@RequestParam("carId") int carId,@RequestParam("price") double price, @RequestParam("count") int count) {
			carService.updateCarCountandPrice(carId,price, count);
		return "redirect:/seller?cardetailsupdated";
	}
	
	@GetMapping("/seller/update")
	public String changeCC(@RequestParam("id") int carId,Model model) {
		model.addAttribute("carId",carId);
		return "sellerupdate";
	}

}
