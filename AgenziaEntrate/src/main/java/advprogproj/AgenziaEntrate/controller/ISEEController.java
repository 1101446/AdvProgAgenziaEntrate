package advprogproj.AgenziaEntrate.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import advprogproj.AgenziaEntrate.model.entities.UserISEE;
import advprogproj.AgenziaEntrate.services.ISEEService;
import advprogproj.AgenziaEntrate.services.UserISEEService;
import advprogproj.AgenziaEntrate.services.UserService;
//import ch.qos.logback.classic.Logger;

@RequestMapping("/isees")
@Controller
public class ISEEController {
	//private final Logger logger = (Logger) LoggerFactory.getLogger(RealEstateController.class);
	private ISEEService iseeService;
	private UserISEEService userISEEService;
	private UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model iseeModel) {
		//logger.info("Listing RealEstates");
		List<ISEE> allISEEs = new ArrayList<ISEE>();
		Set<UserISEE> allUserISEEs = new HashSet<UserISEE>();
		int numISEEs = -1;
		int numUserISEEs = -1;
		try {
			allISEEs = this.iseeService.findAllISEEs();
			numISEEs = allISEEs.size();
			
			allUserISEEs = this.userISEEService.findAllUserISEEs();
			numUserISEEs = allUserISEEs.size();
		}catch(Exception e) {
			//logger.error(e.getMessage());
		}
		
		iseeModel.addAttribute("allISEEs", allISEEs);
		iseeModel.addAttribute("numISEEs", numISEEs);
		iseeModel.addAttribute("allUserISEEs", allUserISEEs);
		iseeModel.addAttribute("numUserISEEs", numUserISEEs);
		return "isees/list";
	}
	
	@PostMapping(value = "/profile")
	public String getProfile(@RequestParam("email") String email, Model iseeModel) {
		//logger.info("Listing RealEstates");
		User profile = this.userService.findUserEmail(email);
		Set<UserISEE> profileISEEs = this.userService.getAssociatedISEEs(profile);
		iseeModel.addAttribute("profileISEEs", profileISEEs);
		iseeModel.addAttribute("currentYear", LocalDate.now().getYear());
		return "isees/list";
	}
	
	@PostMapping(value = "/evaluate")
	public String evaluateISEE(@RequestParam("user") String user, @RequestParam("year") int year) {
		//logger.info("Listing RealEstates");
		User profile = this.userService.findUserEmail(user);
		this.userService.evaluateISEE(profile, year);
		return "redirect:/";
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
	public String saveUserISEE(@PathVariable("iseeId") long iseeId,
									  @PathVariable("userId") String userId) {
		this.userISEEService.create(userId, iseeId);
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
	public String editISEE(@PathVariable("iseeId") long iseeId, Model iseeModel) {
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
		this.userISEEService.create(userId, isee);
		return "redirect:/isees/list";
	}
	
	@GetMapping(value = "/{iseeId}/user/{userId}/unlink" )
	public String unlinkISEE( 
			@PathVariable("iseeId") long iseeId, 
			@PathVariable("userId") String userId){
		this.userISEEService.delete(userId, iseeId);
		return "redirect:/isees/list";
	}
	
	@GetMapping(value = "/{iseeId}/delete")
	public String delete(@PathVariable("iseeId") long iseeId){
		this.iseeService.delete(iseeId);
		return "redirect:/isees/list";
	}
	
	@Autowired
	public void setISEEService(ISEEService iseeService) {
		this.iseeService = iseeService;
	}
	
	@Autowired
	public void setUserISEEService(UserISEEService userISEEService) {
		this.userISEEService = userISEEService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
