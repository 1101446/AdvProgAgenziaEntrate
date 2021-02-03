package advprogproj.AgenziaEntrate.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.LoggerFactory;
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

import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.services.UserRealEstateService;
import advprogproj.AgenziaEntrate.services.RealEstateService;
import advprogproj.AgenziaEntrate.services.UserService;
import ch.qos.logback.classic.Logger;

@RequestMapping("/realestates")
@Controller
public class RealEstateController {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(InstitutionController.class);
	private RealEstateService realEstateService;
	private UserRealEstateService userRealEstateService;
	private UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model inModel) {
		logger.info("Listing RealEstates");
		List<RealEstate> allRealEstates = new ArrayList<RealEstate>();
		int numRealEstates = -1;
		
		try {
			allRealEstates = this.realEstateService.findAllRealEstates();
			numRealEstates = allRealEstates.size();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		inModel.addAttribute("realEstates", allRealEstates);
		inModel.addAttribute("numRealEstates", numRealEstates);
		
		return "realestates/list";
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("newRealEstate") RealEstate newRealEstate, 
			   		   @ModelAttribute("userIds") List<User> userIds, 
			   		   @ModelAttribute("userRealEstate") List<UserRealEstate> ures, BindingResult br) {
		this.realEstateService.update(newRealEstate);
		if(ures.size() > 0) {
			for(UserRealEstate userRealEstate : ures) {
				this.userRealEstateService.update(userRealEstate);
				this.userService.update(userRealEstate.getUser());
			}
			this.realEstateService.update(newRealEstate);
		}
		return "redirect:/realestates/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model inModel) {
		List<User> users = this.userService.findAllUsers();
		inModel.addAttribute("newRealEstate", new RealEstate());
		inModel.addAttribute("users", users);
		inModel.addAttribute("userRealEstate", new HashSet<UserRealEstate>());
		return "realestates/list";
	}
	
	@GetMapping(value = "/{realEstateId}/edit")
	public String edit(@PathVariable("realEstateId") long realEstateId, Model inModel) {
		RealEstate re = this.realEstateService.findRealEstate(realEstateId);
		inModel.addAttribute("newRealEstate", re);
		return "realestates/form";
	}
	
	@GetMapping(value = "/{realEstateId}/delete/")
	public String delete(@PathVariable("realEstateId") long realEstateId) {
		this.realEstateService.delete(realEstateId);
		return "redirect:/realestates/list/";
	}
	
	@Autowired
	public void setRealEstateService(RealEstateService realEstateService) {
		this.realEstateService = realEstateService;
	}
}
