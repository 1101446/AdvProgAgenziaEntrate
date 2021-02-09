package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.dao.UserVehicleDao;
import advprogproj.AgenziaEntrate.model.dao.VehicleDao;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;

@Service("userVehicle")
public class UserVehicleServiceDefault implements UserVehicleService{
	
	private UserVehicleDao userVehicleDao;
	private UserDao userDao;
	private VehicleDao vehicleDao;
	
	@Transactional
	@Override
	public Set<UserVehicle> findAllUserVehicles() {
		return this.userVehicleDao.findAll();
	}
	
	@Transactional
	@Override
	public UserVehicle findUserVehicle(String user, long vehicle, LocalDate date) {
		return this.userVehicleDao.findById(this.userDao.findById(user), this.vehicleDao.findById(vehicle), date);
	}
	
	@Transactional
	@Override
	public UserVehicle create(String user, long vehicle, LocalDate endOfYear, long price) {
		User u = this.userDao.findById(user);
		Vehicle v = this.vehicleDao.findById(vehicle);
		UserVehicle newUserVehicle = this.userVehicleDao.create(u, v, endOfYear, price);
		return newUserVehicle;
	}
	
	@Transactional
	@Override
	public UserVehicle update(UserVehicle userVehicle) {
		UserVehicle uv = this.userVehicleDao.update(userVehicle);
		this.userDao.update(userVehicle.getUser());
		this.vehicleDao.update(userVehicle.getVehicle());
		this.userDao.update(uv.getUser());
		this.vehicleDao.update(uv.getVehicle());
		return uv;
	}
	
	@Transactional
	@Override
	public void delete(String user, long vehicle, LocalDate endOfYear) {
		UserVehicle uv = this.findUserVehicle(user, vehicle, endOfYear);
		this.userDao.removeUserVehicle(uv.getUser(), uv);
		this.vehicleDao.removeUserVehicle(uv.getVehicle(), uv);
		this.userVehicleDao.delete(uv);
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
