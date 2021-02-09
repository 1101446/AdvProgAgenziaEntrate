package advprogproj.AgenziaEntrate.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.dao.UserVehicleDao;
import advprogproj.AgenziaEntrate.model.dao.VehicleDao;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;

@Service("vehicle")
public class VehicleServiceDefault implements VehicleService{
	
	private VehicleDao vehicleDao;
	private UserVehicleDao userVehicleDao;
	private UserDao userDao;
	
	@Transactional
	@Override
	public List<Vehicle> findAllVehicles() {
		return this.vehicleDao.findAll();
	}
	
	@Transactional
	@Override
	public Vehicle findVehicle(long id) {
		return this.vehicleDao.findById(id);
	}
	
	@Transactional
	@Override
	public Vehicle create(String brand, String model, String vehicleRegistration) {
		return this.vehicleDao.create(brand, model, vehicleRegistration);
	}
	
	@Transactional
	@Override
	public Vehicle update(Vehicle vehicle) {
		return this.vehicleDao.update(vehicle);
	}
	
	@Transactional
	@Override
	public void delete(long vehicle) {
		Vehicle v = this.vehicleDao.findById(vehicle);
		for(UserVehicle uv : this.vehicleDao.getUserVehicles(v)) {
			this.userDao.removeUserVehicle(uv.getUser(), uv);
			this.vehicleDao.removeUserVehicle(uv.getVehicle(), uv);
			this.userVehicleDao.delete(uv);
		}
		this.vehicleDao.delete(this.findVehicle(vehicle));
	}
	
	@Autowired
	public void setVehicleDao(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	
	@Autowired
	public void setUserVehicleDao(UserVehicleDao userVehicleDao) {
		this.userVehicleDao = userVehicleDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
