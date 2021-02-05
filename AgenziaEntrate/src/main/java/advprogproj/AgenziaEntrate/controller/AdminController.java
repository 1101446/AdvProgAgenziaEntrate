package advprogproj.AgenziaEntrate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.services.AccessService;
import advprogproj.AgenziaEntrate.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private UserService userService;
	private AccessService accessService;
	
	@PostMapping(value = "/save")
    public String registration(@ModelAttribute("newUser") User newUser, Model model) {
    	this.userService.update(newUser);
        return "redirect:/home";
    }
	
	@RequestMapping(value = "/registration")
    public String registrationPage(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("userAccess", accessService.findAccessByName("UTENTE"));
        return "admin/registration";
    }
}
