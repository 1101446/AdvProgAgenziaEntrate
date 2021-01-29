package advprogproj.AgenziaEntrate.services;

import java.util.Date;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
	public UserVehicle create(String user, int vehicle, Date endOfYear, long price) {
		UserVehicle uv = this.userVehicleDao.create(this.userDao.findById(user), this.vehicleDao.findById(vehicle), endOfYear, price);
		this.userDao.addUserVehicle(this.userDao.findById(user), uv);
		this.vehicleDao.addUserVehicle(this.vehicleDao.findById(vehicle), uv);
		return uv;
	}
	
	@Transactional
	public UserVehicle update(UserVehicle userVehicle) {
		return this.userVehicleDao.update(userVehicle);
	}
	
	@Transactional
	public void delete(UserVehicle userVehicle) {
		this.userDao.removeUserVehicle(userVehicle.getUser(), userVehicle);
		this.vehicleDao.removeUserVehicle(userVehicle.getVehicle(), userVehicle);
		this.userVehicleDao.delete(userVehicle);
	}
}
