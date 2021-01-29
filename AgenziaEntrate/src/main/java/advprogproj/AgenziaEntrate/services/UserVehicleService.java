package advprogproj.AgenziaEntrate.services;

import java.util.Date;

import org.hibernate.Session;

import advprogproj.AgenziaEntrate.model.entities.UserVehicle;

public interface UserVehicleService {
	
	public UserVehicle findUserVehicle(String user, long vehicle, Date date); 
	
	public UserVehicle create(String user, long vehicle, Date endOfYear, long price);
	
	public UserVehicle update(UserVehicle userVehicle);
	
	public void delete(UserVehicle userVehicle);
}
