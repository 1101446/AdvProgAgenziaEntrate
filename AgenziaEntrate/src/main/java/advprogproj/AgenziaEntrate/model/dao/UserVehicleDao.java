package advprogproj.AgenziaEntrate.model.dao;

import java.time.LocalDate;

import org.hibernate.Session;

import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;

public interface UserVehicleDao {
	Session getSession();
	
	UserVehicle findById(User user, Vehicle vehicle, LocalDate date);
	
	public void setSession(Session session);
	
	UserVehicle create(User user, Vehicle vehicle, LocalDate endOfYear, long price);
	
	UserVehicle update(UserVehicle userVehicle);
	
	void delete(UserVehicle userVehicle);
}
