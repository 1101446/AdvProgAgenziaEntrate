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

import advprogproj.AgenziaEntrate.model.entities.Family;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.services.FamilyService;
import advprogproj.AgenziaEntrate.services.UserService;
//import ch.qos.logback.classic.Logger;

@RequestMapping("/families")
@Controller
public class FamilyController {
	
	//private final Logger logger = (Logger) LoggerFactory.getLogger(RealEstateController.class);
	private FamilyService familyService;
	private UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model inModel) {
		//logger.info("Listing Families");
		List<Family> allFamilies = new ArrayList<Family>();
		int numFamilies = -1;
		
		try {
			allFamilies = this.familyService.findAllFamilies();
			numFamilies = allFamilies.size();
		}catch(Exception e) {
			//logger.error(e.getMessage());
		}
		
		inModel.addAttribute("allFamilies", allFamilies);
		inModel.addAttribute("numFamilies", numFamilies);
		return "families/list";
	}
	
	@PostMapping(value = "/save")
	public String save(@RequestParam("id") long id, 
					   @RequestParam("userId") String userid,
					   @RequestParam("hierarchy") String hierarchy,
					   @RequestParam("houseHolder") String houseHolder,
					   BindingResult br) {
		Family f = new Family();
		f.setId(id);
		f.setUser(this.userService.findUser(userid));
		f.setHierarchy(hierarchy);
		f.setHouseHolder(houseHolder);
		this.familyService.update(f);
		
		return "redirect:/families/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model inModel) {
		List<User> users = this.userService.findAllUsers();
		inModel.addAttribute("users", users);

		return "families/form";
	}
	
	@GetMapping(value = "/{familyId}/{userId}/edit")
	public String edit(@PathVariable("familyId") long familyId,
					   @PathVariable("userId") String userId, Model inModel) {
		Family f = this.familyService.findFamily(familyId, userId);
		inModel.addAttribute("family", f);
		return "families/form";
	}
	
	@GetMapping(value = "/{familyId}/{userId}/delete/")
	public String delete(@PathVariable("familyId") long familyId,
			   	 	   	 @PathVariable("userId") String userId) {
		this.familyService.delete(familyId,userId);
		return "redirect:/families/list/";
	}
	
	@Autowired
	public void setRealEstateService(FamilyService familyService) {
		this.familyService = familyService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
