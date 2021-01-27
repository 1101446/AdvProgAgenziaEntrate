package advprogproj.AgenziaEntrate.services;

import java.util.Date;

import org.hibernate.Session;

import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;

public interface UserVehicleService {
	
	public UserVehicle create(User user, Vehicle vehicle, Date endOfYear, long price);
	
	public UserVehicle update(UserVehicle userVehicle);
	
	public void delete(UserVehicle userVehicle);
}
