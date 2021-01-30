package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.UserDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.UserVehicleDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.VehicleDaoDefault;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;

@Service("userVehicle")
public class UserVehicleServiceDefault implements UserVehicleService{
	
	private UserVehicleDaoDefault userVehicleDao;
	private UserDaoDefault userDao;
	private VehicleDaoDefault vehicleDao;
	
	@Transactional
	public UserVehicle findUserVehicle(String user, long vehicle, LocalDate date) {
		return this.userVehicleDao.findById(this.userDao.findById(user), this.vehicleDao.findById(vehicle), date);
	}
	
	@Transactional
	public UserVehicle create(String user, long vehicle, LocalDate endOfYear, long price) {
		return this.userVehicleDao.create(this.userDao.findById(user), this.vehicleDao.findById(vehicle), endOfYear, price);
	}
	
	@Transactional
	public UserVehicle update(UserVehicle userVehicle) {
		return this.userVehicleDao.update(userVehicle);
	}
	
	@Transactional
	public void delete(UserVehicle userVehicle) {
		this.userVehicleDao.delete(userVehicle);
	}
	
	@Autowired
	public void setUserVehicleDao(UserVehicleDaoDefault userVehicleDao) {
		this.userVehicleDao = userVehicleDao;
	}
	
	@Autowired
	public void setUserDao(UserDaoDefault userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setVehicleDao(VehicleDaoDefault vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	
	
}
