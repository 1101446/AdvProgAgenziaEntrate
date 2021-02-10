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
import org.springframework.web.bind.annotation.RequestParam;

import advprogproj.AgenziaEntrate.model.entities.Access;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.services.AccessService;
import advprogproj.AgenziaEntrate.services.UserService;

@RequestMapping("/users")
@Controller
public class UserController {
	private UserService userService;
	private AccessService accessService;
	
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
	
	@PostMapping(value = "/profile")
	public String getProfile(@RequestParam("email") String email, Model userModel) {
		//logger.info("Listing RealEstates");
		User profile = this.userService.findUserEmail(email);
		userModel.addAttribute("profile", profile);
		return "users/list";
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("user") User user, 
					   @RequestParam("accessId") long access,
					   @RequestParam("isHandicap") boolean handicap, BindingResult br) {
		User u = user;
		Access a = this.accessService.findAccess(access);
		u.setHandicap(handicap);
		u.setAccess(a);
		this.userService.update(u);
		return "redirect:/users/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model userModel) {
		List<Access> allAccess = this.accessService.findAllAccess();
		allAccess.removeIf(a -> a.getRoleName().equals("ADMIN"));
		userModel.addAttribute("user", new User());
		userModel.addAttribute("allAccess", allAccess);
		return "users/form";
	}
	
	@GetMapping(value = "/{userId}/edit")
	public String edit(@PathVariable("userId") String userId, Model userModel) {
		List<Access> allAccess= this.accessService.findAllAccess();
		allAccess.remove(this.accessService.findAccessByName("ADMIN"));
		User u = this.userService.findUser(userId);
		userModel.addAttribute("allAccess", allAccess);
		userModel.addAttribute("user", u);
		return "users/form";
	}
	
	@GetMapping(value = "/{userId}/delete")
	public String delete(@PathVariable("userId") String userId) {
		this.userService.delete(userId);
		return "redirect:/users/list";
	}
	
	@GetMapping("/link/choose")
	public String addRole(Model accessModel) {
		List<User> users = this.userService.findAllUsers();
		List<Access> access = this.accessService.findAllAccess();
		access.removeIf(a -> a.getRoleName().equals("ADMIN"));
		accessModel.addAttribute("access",access);
		accessModel.addAttribute("users",users);
		return "users/link_choose";
	}
	
	@PostMapping("/link")
	public String link(@RequestParam(value="access") long accessId,
					   @RequestParam(value="user") String userId) {
		User u = this.userService.findUser(userId);
		Access a = this.accessService.findAccess(accessId);
		u.setAccess(a);
		this.userService.update(u);
		return "redirect:/users/list";
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setAccesService(AccessService accessService) {
		this.accessService = accessService;
	}
	
}
