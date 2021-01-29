package advprogproj.AgenziaEntrate.model.dao;

import java.util.Date;

import org.hibernate.Session;

import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;

public interface UserVehicleDao {
	Session getSession();
	
	UserVehicle findById(User user, Vehicle vehicle, Date date);
	
	public void setSession(Session session);
	
	UserVehicle create(User user, Vehicle vehicle, Date endOfYear, long price);
	
	UserVehicle update(UserVehicle userVehicle);
	
	void delete(UserVehicle userVehicle);
}
