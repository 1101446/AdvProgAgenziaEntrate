package advprogproj.AgenziaEntrate.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import advprogproj.AgenziaEntrate.model.entities.Access;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.services.AccessService;
import advprogproj.AgenziaEntrate.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	String appName;

	private UserService userService;
	private AccessService accessService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Date date = new Date();
		System.out.println("Home Page Requested,  locale = " + locale);
		
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("appName", appName);
		
		return "home";
	}
	
	@PostMapping(value = "/registration/save")
    public String registration(@ModelAttribute("newUser") User newUser, 
    						   @RequestParam(value="isHandicap") boolean handicap) {
        Access a = accessService.findAccessByName("UTENTE");
        newUser.setHandicap(handicap);
        newUser.setAccess(a);
    	this.userService.update(newUser);
        return "redirect:/home";
    }
	
	@GetMapping(value = "/registration")
    public String registrationPage(Model model) {
        model.addAttribute("newUser", new User());
        return "registration";
    }
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}
	
}
