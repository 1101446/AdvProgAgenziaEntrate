package advprogproj.AgenziaEntrate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.services.UserService;

@RequestMapping("/users")
@Controller
public class UserController {
	private UserService userService;
	
	@GetMapping(value = "/list")
	public String list(Model userModel) {
		//logger.info("Listing RealEstates");
		List<User> allUsers = new ArrayList<User>();
		int numUsers = -1;
		allUsers = this.userService.findAllUsers();
		numUsers = allUsers.size();
		
		userModel.addAttribute("allUsers", allUsers);
		userModel.addAttribute("numUsers", numUsers);
		return "users/list";
	}
	
	@GetMapping(value = "/profile/{userId}")
	public String getProfile(@PathVariable("userId") String userId, Model userModel) {
		//logger.info("Listing RealEstates");
		User profile = this.userService.findUserEmail(userId);
		userModel.addAttribute("profile", profile);
		return "users/profile";
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("user") User newUser, BindingResult br) {
		this.userService.update(newUser);
		return "redirect:/users/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model inModel) {
		inModel.addAttribute("user", new User());
		return "users/form";
	}
	
	@GetMapping(value = "/{userId}/edit")
	public String edit(@PathVariable("userId") String userId, Model userModel) {
		User u = this.userService.findUser(userId);
		userModel.addAttribute("user", u);
		return "users/form";
	}
	
	@GetMapping(value = "/{userId}/delete/")
	public String delete(@PathVariable("userId") String userId) {
		this.userService.delete(userId);
		return "redirect:/users/list/";
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
