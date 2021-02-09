package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.Set;

import advprogproj.AgenziaEntrate.model.entities.UserVehicle;

public interface UserVehicleService {
	
	public Set<UserVehicle> findAllUserVehicles();
	
	public UserVehicle findUserVehicle(String user, long vehicle, LocalDate date); 
	
	public UserVehicle create(String user, long vehicle, LocalDate endOfYear, long price);
	
	public UserVehicle update(UserVehicle userVehicle);
	
	public void delete(String user, long vehicle, LocalDate endOfYear);
}
