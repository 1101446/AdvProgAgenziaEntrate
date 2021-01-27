package advprogproj.AgenziaEntrate.services;

import java.util.Date;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;

@Service("userVehicle")
public class UserVehicleServiceDefault implements UserVehicleService{
	
	public UserVehicle create(User user, Vehicle vehicle, Date endOfYear, long price) {
		UserVehicle userVehicle = new UserVehicle();
		userVehicle.setUser(user);
		userVehicle.setVechicle(vehicle);
		userVehicle.setEndOfYear(endOfYear);
		userVehicle.setPrice(price);
		this.getSession().save(userVehicle);
		return userVehicle;
	}
	
	public UserVehicle update(UserVehicle userVehicle) {
		return (UserVehicle)this.getSession().merge(userVehicle);
	}
	
	public void delete(UserVehicle userVehicle) {
		this.getSession().delete(userVehicle);
	}
}