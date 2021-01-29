package advprogproj.AgenziaEntrate.services;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.VehicleDaoDefault;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;

@Service("vehicle")
public class VehicleServiceDefault implements VehicleService{
	
	private VehicleDaoDefault vehicleDao;
	
	@Transactional
	public Vehicle create(String brand, String model, String vehicleRegistration) {
		return this.vehicleDao.create(brand, model, vehicleRegistration);
	}
	
	@Transactional
	public Vehicle update(Vehicle vehicle) {
		return this.vehicleDao.update(vehicle);
	}
	
	@Transactional
	public void delete(Vehicle vehicle) {
		this.vehicleDao.delete(vehicle);
	}
	
}
