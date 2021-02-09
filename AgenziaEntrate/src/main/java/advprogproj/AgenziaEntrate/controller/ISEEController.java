package advprogproj.AgenziaEntrate.controller;

import java.util.ArrayList;
import java.util.List;

//import org.slf4j.LoggerFactory;
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

import advprogproj.AgenziaEntrate.model.entities.ISEE;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.services.ISEEService;
import advprogproj.AgenziaEntrate.services.UserService;
//import ch.qos.logback.classic.Logger;

@RequestMapping("/isees")
@Controller
public class ISEEController {
	
	//private final Logger logger = (Logger) LoggerFactory.getLogger(RealEstateController.class);
	private ISEEService iseeService;
	private UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model iseeModel) {
		//logger.info("Listing RealEstates");
		List<ISEE> allISEEs = new ArrayList<ISEE>();
		int numISEEs = -1;
		
		try {
			allISEEs = this.iseeService.findAllISEEs();
			numISEEs = allISEEs.size();
		}catch(Exception e) {
			//logger.error(e.getMessage());
		}
		
		iseeModel.addAttribute("allISEEs", allISEEs);
		iseeModel.addAttribute("numISEEs", numISEEs);
		return "isees/list";
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("ISEE") ISEE isee,
			 		   @RequestParam(value="userId") String userId) {
		ISEE i = this.iseeService.update(isee);
		if(!userId.equals("noUser")) 
			return "redirect:/isees/save/"+i.getId()+"/"+userId;
		return "redirect:/isees/list";
	}
	
	@GetMapping(value = "/save/{iseeId}/{userId}")
	public String saveISEEUser(@PathVariable("iseeId") long iseeId,
									  @PathVariable("userId") String userId) {
		this.iseeService.addAssociatedUser(iseeId, userId);
		return "redirect:/isees/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model iseeModel) {
		List<User> users = this.userService.findAllUsers();
		iseeModel.addAttribute("users", users);
		iseeModel.addAttribute("isee", new ISEE());
		return "isees/form";
	}
	
	@GetMapping(value = "/{iseeId}/edit")
	public String edit(@PathVariable("iseeId") long iseeId, Model iseeModel) {
		ISEE isee = this.iseeService.findISEE(iseeId);
		List<User> users = this.userService.findAllUsers();
		iseeModel.addAttribute("isee", isee);
		iseeModel.addAttribute("users", users);
		return "isees/form";
	}
	
	@GetMapping("/link/choose")
	public String link(Model iseeModel) {
		iseeModel.addAttribute("isees", this.iseeService.findAllISEEs());
		iseeModel.addAttribute("users", this.userService.findAllUsers());
		
		return "isees/link_choose";
	}
	
	@PostMapping("/link")
	public String link(@RequestParam(value="isee") long isee,
					   @RequestParam(value="user") String userId){
		this.iseeService.addAssociatedUser(isee, userId);
		return "redirect:/isees/list";
	}
	
	@GetMapping(value = "/{iseeId}/user/{userId}/unlink" )
	public String unlinkISEE( 
			@PathVariable("iseeId") long iseeId, 
			@PathVariable("userId") String userId){
		this.iseeService.removeAssociatedUser(iseeId, userId);
		return "redirect:/isees/list";
	}
	
	@GetMapping(value = "/{iseeId}/delete")
	public String delete(@PathVariable("iseeId") long iseeId){
		this.iseeService.delete(iseeId);
		return "redirect:/isees/list/";
	}
	
	@Autowired
	public void setISEEService(ISEEService iseeService) {
		this.iseeService = iseeService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
