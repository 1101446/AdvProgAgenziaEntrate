package advprogproj.AgenziaEntrate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
public class SecurityController 
{
	@Autowired
	String appName;
	
	private UserService userService;
	private AccessService accessService;
	
	@GetMapping(value = "/login")
    public String login(@RequestParam(value = "error", required = false) String error, 
//                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if(error != null) {
        	errorMessage = "Username o Password errati !!";
        }
//        if(logout != null) {
//        	// entriamo in questo caso se non specifichiamo una .logoutSuccessUrl in WebSecurityConf.configure
//        	errorMessage = "Uscita dal sistema avvenuta !!";
//        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("appName", appName);
        return "login";
    }
	
	@PostMapping(value = "/registration/save")
    public String registration(@ModelAttribute("newUser") User newUser, Model model) {
    	this.userService.update(newUser);
        return "redirect:/";
    }
	
	@GetMapping(value = "/registration")
    public String registrationPage(Model model) {
        model.addAttribute("newUser", new User());
        Access a = accessService.findAccessByName("UTENTE");
        model.addAttribute("userAccess", a);
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
//    @GetMapping(value="/logout")
//    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){    
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "redirect:/";
//    }
  
}

