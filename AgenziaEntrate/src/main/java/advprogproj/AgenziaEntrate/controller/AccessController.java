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
	
	@GetMapping(value = "/list")
	public String list(Model accessModel) {
		//logger.info("Listing RealEstates");
		List<Access> allAccess = new ArrayList<Access>();
		int numAccess = -1;
		try {
			allAccess = this.accessService.findAllAccess();
			numAccess = allAccess.size();
		}catch(Exception e) {
			//logger.error(e.getMessage());
		}
		
		accessModel.addAttribute("allAccess", allAccess);
		accessModel.addAttribute("numAccess", numAccess);
		return "roles/list";
	}
	
	@PostMapping(value = "/save")
	public String saveAccess(@ModelAttribute("access") Access newAccess, @RequestParam("userId") String userId) {
		Access a = this.accessService.update(newAccess);
		if(userId != null) {
			return "redirect:/roles/save/"+a.getId()+"/"+userId;
		}
		return "redirect:/roles/list";
	}
	
	@GetMapping(value = "/save/{accessId}/{userId}")
	public String saveUser(@PathVariable("accessId") long accessId, 
						   @PathVariable("userId") String userId, BindingResult br) {
		Access newAccess = this.accessService.findAccess(accessId);
		User u = this.userService.findUser(userId);
		u.setAccess(newAccess);
		this.userService.update(u);
		return "redirect:/roles/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model accessModel) {
		List<User> users = this.userService.findAllUsers();
		accessModel.addAttribute("access", new Access());
		accessModel.addAttribute("users", users);

		return "roles/form";
	}
	
	@GetMapping(value = "/{accessId}/edit")
	public String edit(@PathVariable("accessId") long accessId, Model accessModel) {
		Access a = this.accessService.findAccess(accessId);
		accessModel.addAttribute("access", a);
		return "roles/form";
	}
	
	@GetMapping(value = "/{accessId}/delete/")
	public String delete(@PathVariable("accessId") long accessId) {
		this.userService.replaceAccess(accessId);
		this.accessService.delete(accessId);
		return "redirect:/roles/list/";
	}
	
	@GetMapping("/link/choose")
	public String addRole(Model accessModel) {
		List<User> users = this.userService.findAllUsers();
		List<Access> access = this.accessService.findAllAccess();
		accessModel.addAttribute("access",access);
		accessModel.addAttribute("users",users);
		return "roles/link_choose";
	}
	
	@PostMapping("/link")
	public String link(
			@RequestParam(value="next", required=false) String next,
			@RequestParam(value="access") long accessId,
			@RequestParam(value="user") String userId) {
		User u = this.userService.findUser(userId);
		Access a = this.accessService.findAccess(accessId);
		u.setAccess(a);
		this.userService.update(u);
		
		if (next == null || next.length() == 0) {
			next = "/roles/list";
		}
		
		return "redirect:" + next;
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
