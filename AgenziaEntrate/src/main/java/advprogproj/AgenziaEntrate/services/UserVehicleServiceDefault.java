package advprogproj.AgenziaEntrate.services;

import java.util.Date;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import advprogproj.AgenziaEntrate.model.dao.UserDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.UserVehicleDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.VehicleDaoDefault;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;

@Service("userVehicle")
public class UserVehicleServiceDefault implements UserVehicleService{
	private UserVehicleDaoDefault userVehicleDao;
	private UserDaoDefault userDao;
	private VehicleDaoDefault vehicleDao;
	
	public UserVehicle create(User user, Vehicle vehicle, Date endOfYear, long price) {
		this.vehicleDao.(vehicle);
		return this.userVehicleDao.create(user, vehicle, endOfYear, price);
	}
	
	public UserVehicle update(UserVehicle userVehicle) {
		
	}
	
	public void delete(UserVehicle userVehicle) {

	}
}
