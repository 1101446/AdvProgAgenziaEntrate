package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.dao.UserVehicleDao;
import advprogproj.AgenziaEntrate.model.dao.VehicleDao;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;

@Service("userVehicle")
public class UserVehicleServiceDefault implements UserVehicleService{
	
	private UserVehicleDao userVehicleDao;
	private UserDao userDao;
	private VehicleDao vehicleDao;
	
	@Transactional
	@Override
	public UserVehicle findUserVehicle(String user, long vehicle, LocalDate date) {
		return this.userVehicleDao.findById(this.userDao.findById(user), this.vehicleDao.findById(vehicle), date);
	}
	
	@Transactional
	@Override
	public UserVehicle create(String user, long vehicle, LocalDate endOfYear, long price) {
		return this.userVehicleDao.create(this.userDao.findById(user), this.vehicleDao.findById(vehicle), endOfYear, price);
	}
	
	@Transactional
	@Override
	public UserVehicle update(UserVehicle userVehicle) {
		return this.userVehicleDao.update(userVehicle);
	}
	
	@Transactional
	@Override
	public void delete(UserVehicle userVehicle) {
		this.userVehicleDao.delete(userVehicle);
	}
	
	@Autowired
	public void setUserVehicleDao(UserVehicleDao userVehicleDao) {
		this.userVehicleDao = userVehicleDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setVehicleDao(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
}
