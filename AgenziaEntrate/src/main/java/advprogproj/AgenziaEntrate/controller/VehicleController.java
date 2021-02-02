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

import advprogproj.AgenziaEntrate.model.entities.Vehicle;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;
import advprogproj.AgenziaEntrate.services.UserVehicleService;
import advprogproj.AgenziaEntrate.services.VehicleService;
import advprogproj.AgenziaEntrate.services.UserService;
import ch.qos.logback.classic.Logger;

@RequestMapping("/vehicles")
@Controller
//Portate il pesce nel frigo a nonna.class
public class VehicleController {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(InstitutionController.class);
	private VehicleService vehicleService;
	private UserVehicleService userVehicleService;
	private UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model inModel) {
		logger.info("Listing Vehicles");
		List<Vehicle> allVehicles = new ArrayList<Vehicle>();
		int numVehicles = -1;
		
		try {
			allVehicles = this.vehicleService.findAllVehicles();
			numVehicles = allVehicles.size();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		inModel.addAttribute("vehicles", allVehicles);
		inModel.addAttribute("numVehicles", numVehicles);
		
		return "vehicles/list";
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("newVehicle") Vehicle newVehicle, 
			   		   @ModelAttribute("userIds") List<User> userIds, 
			   		   @ModelAttribute("userVehicles") List<UserVehicle> uvs, BindingResult br) {
		this.vehicleService.update(newVehicle);
		if(uvs.size() > 0) {
			for(UserVehicle userVehicle : uvs) {
				this.userVehicleService.update(userVehicle);
				this.userService.update(userVehicle.getUser());
			}
			this.vehicleService.update(newVehicle);
		}
		return "redirect:/vehicles/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model inModel) {
		List<User> users = this.userService.findAllUsers();
		inModel.addAttribute("newVehicle", new Vehicle());
		inModel.addAttribute("users", users);
		inModel.addAttribute("userVehicles", new HashSet<UserVehicle>());
		return "vehicles/list";
	}
	
	@GetMapping(value = "/{vehicleId}/edit")
	public String edit(@PathVariable("vehicleId") long vehicleId, Model inModel) {
		Vehicle ve = this.vehicleService.findVehicle(vehicleId);
		inModel.addAttribute("newRealEstate", ve);
		return "vehicles/form";
	}
	
	@GetMapping(value = "/{vehicleId}/delete/")
	public String delete(@PathVariable("vehicleId") long vehicleId) {
		this.vehicleService.delete(vehicleId);
		return "redirect:/vehicles/list/";
	}
	
	@Autowired
	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
	
	@Autowired
	public void setUserVehicleService(UserVehicleService userVehicleService) {
		this.userVehicleService = userVehicleService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}