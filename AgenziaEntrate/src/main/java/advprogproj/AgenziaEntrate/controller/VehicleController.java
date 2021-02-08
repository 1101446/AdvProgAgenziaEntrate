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

import advprogproj.AgenziaEntrate.model.entities.Vehicle;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;
import advprogproj.AgenziaEntrate.services.UserVehicleService;
import advprogproj.AgenziaEntrate.services.VehicleService;
import advprogproj.AgenziaEntrate.services.UserService;
//import ch.qos.logback.classic.Logger;

@RequestMapping("/vehicles")
@Controller
public class VehicleController {
	
	//private final Logger logger = (Logger) LoggerFactory.getLogger(VehicleController.class);
	private VehicleService vehicleService;
	private UserVehicleService userVehicleService;
	private UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model vehicleModel) {
		//logger.info("Listing Vehicles");
		List<Vehicle> allVehicles = new ArrayList<Vehicle>();
		int numVehicles = -1;
		allVehicles = this.vehicleService.findAllVehicles();
		numVehicles = allVehicles.size();
		vehicleModel.addAttribute("vehicles", allVehicles);
		vehicleModel.addAttribute("numVehicles", numVehicles);
		
		return "vehicles/list";
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("vehicle") Vehicle newVehicle, 
			   		   //@ModelAttribute("userVehicle") UserVehicle uv, 
						BindingResult br) {
		this.vehicleService.update(newVehicle);
		/*if(uv != null) {
			this.userVehicleService.update(uv);
			this.userService.update(uv.getUser());
			this.vehicleService.update(newVehicle);
		}*/
		return "redirect:/vehicles/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model vehicleModel) {
		List<User> users = this.userService.findAllUsers();
		vehicleModel.addAttribute("vehicle", new Vehicle());
		vehicleModel.addAttribute("users", users);
		vehicleModel.addAttribute("userVehicle", new UserVehicle());
		return "vehicles/form";
	}
	
	@GetMapping(value = "/{vehicleId}/edit")
	public String edit(@PathVariable("vehicleId") long vehicleId, Model vehicleModel) {
		List<User> users = this.userService.findAllUsers();
		Vehicle ve = this.vehicleService.findVehicle(vehicleId);
		vehicleModel.addAttribute("users", users);
		vehicleModel.addAttribute("vehicle", ve);
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