package advprogproj.AgenziaEntrate.model.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import advprogproj.AgenziaEntrate.model.entities.UserVehicle;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;

@Repository("vehicleDao")
public class VehicleDaoDefault extends DefaultDao implements VehicleDao{
	
	public List<Vehicle> findAll(){
		return this.getSession().createQuery("from Vehicle a",Vehicle.class).getResultList();
	}
	
	public Vehicle findById(long id) {
		return this.getSession().find(Vehicle.class, id);
	}
	
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
	
	
	public Set<UserVehicle> getUserVehicles(Vehicle vehicle) {
		Query q = this.getSession().createQuery("from UserVehicle a JOIN FETCH a.vehicle WHERE a.vehicle = :vehicle", UserVehicle.class);
		return new HashSet<UserVehicle>(q.setParameter("vehicle", vehicle).getResultList());
	}
}
