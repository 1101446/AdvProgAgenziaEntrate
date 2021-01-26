package advprogproj.AgenziaEntrate.model.dao;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;

public interface VehicleDao {
	Session getSession();
	
	public void setSession(Session session);
	
	Vehicle create(String brand, String model, String vehicleRegistration);
	
	Vehicle update(Vehicle vehicle);
	
	void delete(Vehicle vehicle);
}
