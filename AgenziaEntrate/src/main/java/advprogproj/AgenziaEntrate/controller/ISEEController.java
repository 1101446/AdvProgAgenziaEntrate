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

import advprogproj.AgenziaEntrate.model.entities.ISEE;
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
	public String save(@ModelAttribute("ISEE") ISEE isee,  BindingResult br) {
		this.iseeService.update(isee.getId());
		return "redirect:/isees/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model iseeModel) {
		iseeModel.addAttribute("ISEE", new ISEE());
		return "isees/form";
	}
	
	@GetMapping(value = "/{iseeId}/edit")
	public String edit(@PathVariable("iseeId") long iseeId, Model iseeModel) {
		ISEE i = this.iseeService.findISEE(iseeId);
		iseeModel.addAttribute("isee", i);
		return "isees/form";
	}
	
	@GetMapping(value = "/{iseeId}/delete/")
	public String delete(@PathVariable("iseeId") long iseeId) {
		this.iseeService.delete(iseeId);
		return "redirect:/isees/list/";
	}
	
	@Autowired
	public void setISEEService(ISEEService iseeService) {
		this.iseeService = iseeService;
	}
}
