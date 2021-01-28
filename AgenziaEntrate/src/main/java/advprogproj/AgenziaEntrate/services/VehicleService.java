package advprogproj.AgenziaEntrate.services;

import java.util.Set;

import org.hibernate.Session;

import advprogproj.AgenziaEntrate.model.entities.UserVehicle;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;

public interface VehicleService {
	
	public Vehicle create(String brand, String model, String vehicleRegistration);
	
	public Vehicle update(Vehicle vehicle);
	
	public void delete(Vehicle vehicle);
	
}
