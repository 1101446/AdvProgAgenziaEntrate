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

import advprogproj.AgenziaEntrate.model.entities.Access;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.services.AccessService;
import advprogproj.AgenziaEntrate.services.UserService;
//import ch.qos.logback.classic.Logger;

@RequestMapping("/roles")
@Controller
public class AccessController {
	
	//private final Logger logger = (Logger) LoggerFactory.getLogger(RealEstateController.class);
	private AccessService accessService;
	private UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model inModel) {
		//logger.info("Listing RealEstates");
		List<Access> allAccess = new ArrayList<Access>();
		int numAccess = -1;
		
		try {
			allAccess = this.accessService.findAllAccess();
			numAccess = allAccess.size();
		}catch(Exception e) {
			//logger.error(e.getMessage());
		}
		
		inModel.addAttribute("allAccess", allAccess);
		inModel.addAttribute("numAccess", numAccess);
		return "roles/list";
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("access") Access newAccess, BindingResult br) {
		this.accessService.update(newAccess);
		
		return "redirect:/roles/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model inModel) {
		List<User> users = this.userService.findAllUsers();
		inModel.addAttribute("access", new Access());
		inModel.addAttribute("users", users);

		return "roles/list";
	}
	
	@GetMapping(value = "/{accessId}/edit")
	public String edit(@PathVariable("accessId") long accessId, Model inModel) {
		Access a = this.accessService.findAccess(accessId);
		inModel.addAttribute("access", a);
		return "roles/form";
	}
	
	@GetMapping(value = "/{accessId}/delete/")
	public String delete(@PathVariable("accessId") long accessId,
			   	 	   	 @PathVariable("userId") String userId) {
		this.accessService.delete(accessId);
		return "redirect:/roles/list/";
	}
	
	@Autowired
	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
