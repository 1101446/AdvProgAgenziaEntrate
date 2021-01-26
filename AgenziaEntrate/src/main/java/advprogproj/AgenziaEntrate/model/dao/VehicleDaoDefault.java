package advprogproj.AgenziaEntrate.model.dao;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;

public class VehicleDaoDefault extends DefaultDao implements VehicleDao{
	
	public Vehicle create(String brand, String model, String vehicleRegistration) {
		Vehicle vehicle = new Vehicle();
		vehicle.setBrand(brand);
		vehicle.setModel(model);
		vehicle.setVehicleRegistration(vehicleRegistration);
		this.getSession().save(vehicle);
		return vehicle;
	}
	
	public Vehicle update(Vehicle vehicle) {
		return (Vehicle)this.getSession().merge(vehicle);
	}
	
	public void delete(Vehicle vehicle) {
		this.getSession().delete(vehicle);
	}
}
