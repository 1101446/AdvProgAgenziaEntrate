package advprogproj.AgenziaEntrate.model.dao;

import java.util.Date;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;

@Repository("userVehicleDao")
public class UserVehicleDaoDefault extends DefaultDao implements UserVehicleDao{
	
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
