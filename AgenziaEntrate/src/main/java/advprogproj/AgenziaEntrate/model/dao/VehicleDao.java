package advprogproj.AgenziaEntrate.model.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import advprogproj.AgenziaEntrate.model.entities.UserVehicle;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;

public interface VehicleDao {
	Session getSession();
	
	public void setSession(Session session);
	
	List<Vehicle> findAll();
	
	Vehicle findById(long id);
	
	Vehicle create(String brand, String model, String vehicleRegistration);
	
	Vehicle update(Vehicle vehicle);
	
	void delete(Vehicle vehicle);
	
	public void addUserVehicle(Vehicle vehicle, UserVehicle userVehicle);
	
	public void removeUserVehicle(Vehicle vehicle, UserVehicle userVehicle);
	
	public Set<UserVehicle> getUserVehicles(Vehicle vehicle);
}
