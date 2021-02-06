package advprogproj.AgenziaEntrate.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.VehicleDao;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;

@Service("vehicle")
public class VehicleServiceDefault implements VehicleService{
	
	private VehicleDao vehicleDao;
	
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
		this.vehicleDao.delete(this.findVehicle(vehicle));
	}
	
	@Autowired
	public void setVehicleDao(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	
}
