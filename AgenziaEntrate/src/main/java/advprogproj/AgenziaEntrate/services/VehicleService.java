package advprogproj.AgenziaEntrate.services;

import java.util.List;

import advprogproj.AgenziaEntrate.model.entities.Vehicle;

public interface VehicleService {
	
	public List<Vehicle> findAllVehicles();
	
	public Vehicle findVehicle(long id);
	
	public Vehicle create(String brand, String model, String vehicleRegistration);
	
	public Vehicle update(Vehicle vehicle);
	
	public void delete(long vehicle);
	
}
