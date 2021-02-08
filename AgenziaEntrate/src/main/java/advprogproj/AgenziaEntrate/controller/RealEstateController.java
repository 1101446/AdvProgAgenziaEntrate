package advprogproj.AgenziaEntrate.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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

import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.services.UserRealEstateService;
import advprogproj.AgenziaEntrate.services.RealEstateService;
import advprogproj.AgenziaEntrate.services.UserService;
//import ch.qos.logback.classic.Logger;

@RequestMapping("/realestates")
@Controller
public class RealEstateController {
	
	//private final Logger logger = (Logger) LoggerFactory.getLogger(RealEstateController.class);
	private RealEstateService realEstateService;
	private UserRealEstateService userRealEstateService;
	private UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model reModel) {
		//logger.info("Listing RealEstates");
		List<RealEstate> allRealEstates = new ArrayList<RealEstate>();
		int numRealEstates = -1;
		allRealEstates = this.realEstateService.findAllRealEstates();
		numRealEstates = allRealEstates.size();
		
		reModel.addAttribute("realEstates", allRealEstates);
		reModel.addAttribute("numRealEstates", numRealEstates);
		return "realestates/list";
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("realEstate") RealEstate newRealEstate, 
			   		   @RequestParam("userId") String userId,
			   		   @RequestParam("endOfYear") LocalDate endOfYear,
			   		   @RequestParam("price") int price) {
		RealEstate re = this.realEstateService.update(newRealEstate);
		if(!userId.equals("noUser"))
			return "redirect:/realestates/save/"+re.getId()+"/"+userId.toString()+"/"+endOfYear+"/"+price;
		else
			return "redirect:/realestates/list";
	}
	
	@GetMapping(value = "/save/{realEstateId}/{userId}/{endOfYear}/{price}")
	public String saveUserRealEstate(@PathVariable("realEstateId") long realEstateId,
									 @PathVariable("userId") String userId,
									 @PathVariable("endOfYear") String endOfYear,
									 @PathVariable("price") int price) {
		this.userRealEstateService.create(userId, realEstateId, LocalDate.parse(endOfYear), price);
		return "redirect:/realestates/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model reModel) {
		List<User> users = this.userService.findAllUsers();
		reModel.addAttribute("realEstate", new RealEstate());
		reModel.addAttribute("users", users);
		reModel.addAttribute("userRealEstate", new UserRealEstate());
		return "realestates/form";
	}
	
	@GetMapping(value = "/{realEstateId}/edit")
	public String edit(@PathVariable("realEstateId") long realEstateId, Model reModel) {
		List<User> users = this.userService.findAllUsers();
		RealEstate re = this.realEstateService.findRealEstate(realEstateId);
		reModel.addAttribute("realEstate", re);
		reModel.addAttribute("users", users);
		return "realestates/form";
	}
	
	@GetMapping(value = "/{realEstateId}/delete")
	public String delete(@PathVariable("realEstateId") long realEstateId) {
		this.realEstateService.delete(realEstateId);
		return "redirect:/realestates/list";
	}
	
	@GetMapping("/link/choose")
	public String link(Model institutionModel) {
		institutionModel.addAttribute("realEstate", this.realEstateService.findAllRealEstates());
		institutionModel.addAttribute("users", this.userService.findAllUsers());
		
		return "realestates/link_choose";
	}
	
	@PostMapping("/link")
	public String link(@RequestParam(value="realEstate") long realEstate,
					   @RequestParam(value="user") String userId,
					   @RequestParam(value="endOfYear") String endOfYear,
					   @RequestParam(value="price") int price){
		this.userRealEstateService.create(userId, realEstate, LocalDate.parse(endOfYear), price);
		return "redirect:/realestates/list";
	}
	
	@GetMapping(value = "/realEstate/{realEstateId}/user/{userId}/endOfYear/{endOfYear}/unlink/" )
	public String unlinkUserRealEstates( 
			@PathVariable("realEstateId") long realEstateId, 
			@PathVariable("userId") String userId,
			@PathVariable("endOfYear") String endOfYear){
		this.userRealEstateService.delete(userId,realEstateId,LocalDate.parse(endOfYear));
		return "redirect:/realestates/list";
	}
	
	@Autowired
	public void setRealEstateService(RealEstateService realEstateService) {
		this.realEstateService = realEstateService;
	}
	
	@Autowired
	public void setUserRealEstateService(UserRealEstateService userRealEstateService) {
		this.userRealEstateService = userRealEstateService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
