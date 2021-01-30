package advprogproj.AgenziaEntrate.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.VehicleDaoDefault;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;

@Service("vehicle")
public class VehicleServiceDefault implements VehicleService{
	
	private VehicleDaoDefault vehicleDao;
	
	@Transactional
	public Vehicle findVehicle(long id) {
		return this.vehicleDao.findById(id);
	}
	
	@Transactional
	public Vehicle create(String brand, String model, String vehicleRegistration) {
		return this.vehicleDao.create(brand, model, vehicleRegistration);
	}
	
	@Transactional
	public Vehicle update(Vehicle vehicle) {
		return this.vehicleDao.update(vehicle);
	}
	
	@Transactional
	public void delete(long vehicle) {
		this.vehicleDao.delete(this.findVehicle(vehicle));
	}
	
	@Autowired
	public void setVehicleDao(VehicleDaoDefault vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	
}
