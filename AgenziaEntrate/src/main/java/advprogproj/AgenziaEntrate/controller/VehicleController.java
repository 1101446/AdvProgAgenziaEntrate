package advprogproj.AgenziaEntrate.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

import advprogproj.AgenziaEntrate.model.entities.Vehicle;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;
import advprogproj.AgenziaEntrate.services.UserVehicleService;
import advprogproj.AgenziaEntrate.services.VehicleService;
import advprogproj.AgenziaEntrate.utils.LocalDateAttributeConverter;
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
		Set<UserVehicle> allUserVehicles = this.userVehicleService.findAllUserVehicles();
		List<Vehicle> allVehicles = this.vehicleService.findAllVehicles();
		int numVehicles = -1;
		int numUserVehicles = -1;
		
		numVehicles = allVehicles.size();
		numUserVehicles = allUserVehicles.size();
		vehicleModel.addAttribute("vehicles", allVehicles);
		vehicleModel.addAttribute("userVehicles", allUserVehicles);
		vehicleModel.addAttribute("numVehicles", numVehicles);
		vehicleModel.addAttribute("numUserVehicles", numUserVehicles);
		
		return "vehicles/list";
	}
	
	@PostMapping(value = "/profile")
	public String getProfile(@RequestParam("email") String email, Model vehicleModel) {
		//logger.info("Listing RealEstates");
		User profile = this.userService.findUserEmail(email);
		Set<UserVehicle> profileVehicles = this.userService.getUserVehicles(profile);
		vehicleModel.addAttribute("profileVehicles", profileVehicles);
		return "vehicles/list";
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("vehicle") Vehicle newVehicle, 
			 		   @RequestParam(value="userId") String userId,
			 		   @RequestParam(value="endOfYear") LocalDate endOfYear,
			 		   @RequestParam(value="price") int price) {
		Vehicle v = this.vehicleService.update(newVehicle);
		if(!userId.equals("noUser"))
			return "redirect:/vehicles/save/"+v.getId()+"/"+userId.toString()+"/"+endOfYear+"/"+price;
		else
			return "redirect:/vehicles/list";
	}
	
	@GetMapping(value = "/save/{vehicleId}/{userId}/{endOfYear}/{price}")
	public String saveUserVehicle(@PathVariable("vehicleId") long vehicleId,
									 @PathVariable("userId") String userId,
									 @PathVariable("endOfYear") String endOfYear,
									 @PathVariable("price") int price) {
		this.userVehicleService.create(userId, vehicleId, LocalDate.parse(endOfYear), price);
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
	
	@GetMapping(value = "/{vehicleId}/user/{userId}/endOfYear/{endOfYear}/edit")
	public String editUserVehicle(@PathVariable("vehicleId") long vehicleId,
									 @PathVariable("userId") String userId,
									 @PathVariable("endOfYear") String endOfYear,
									 LocalDateAttributeConverter localStringToDate, Model vehicleModel) {
		UserVehicle uv = this.userVehicleService.findUserVehicle(userId,vehicleId,LocalDate.parse(endOfYear));
		vehicleModel.addAttribute("userVehicle", uv);
		vehicleModel.addAttribute("vehicle", uv.getVehicle());
		vehicleModel.addAttribute("user", uv.getUser());
		vehicleModel.addAttribute("endOfYear", localStringToDate.convertToDatabaseColumn(uv.getEndOfYear()));
		vehicleModel.addAttribute("update", true);
		return "vehicles/link_choose";
	}
	
	@GetMapping(value = "/{vehicleId}/delete")
	public String delete(@PathVariable("vehicleId") long vehicleId) {
		this.vehicleService.delete(vehicleId);
		return "redirect:/vehicles/list";
	}
	
	@GetMapping("/link/choose")
	public String link(Model vehicleModel) {
		vehicleModel.addAttribute("vehicles", this.vehicleService.findAllVehicles());
		vehicleModel.addAttribute("users", this.userService.findAllUsers());
		vehicleModel.addAttribute("update", false);
		
		return "vehicles/link_choose";
	}
	
	@PostMapping("/link")
	public String link(@RequestParam(value="vehicle") long vehicleId,
					   @RequestParam(value="user") String userId,
					   @RequestParam(value="endOfYear") LocalDate endOfYear,
					   @RequestParam(value="price") int price,
					   @RequestParam(value="update") boolean update){
		if(update){
			this.userVehicleService.update(userId, vehicleId, endOfYear, price);
		}
		else
			this.userVehicleService.create(userId, vehicleId, endOfYear, price);
		return "redirect:/vehicles/list";
	}
	
	@GetMapping(value = "/{vehicleId}/user/{userId}/endOfYear/{endOfYear}/unlink" )
	public String unlinkUserVehicles( 
			@PathVariable("vehicleId") long vehicleId, 
			@PathVariable("userId") String userId,
			@PathVariable("endOfYear") String endOfYear){
		this.userVehicleService.delete(userId,vehicleId,LocalDate.parse(endOfYear));
		return "redirect:/vehicles/list";
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