package advprogproj.AgenziaEntrate.model.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import advprogproj.AgenziaEntrate.model.entities.UserVehicle;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;

@Repository("vehicleDao")
public class VehicleDaoDefault extends DefaultDao implements VehicleDao{
	
	@Override
	public List<Vehicle> findAll(){
		return this.getSession().createQuery("from Vehicle a",Vehicle.class).getResultList();
	}
	
	@Override
	public Vehicle findById(long id) {
		return this.getSession().find(Vehicle.class, id);
	}
	
	@Override
	public Vehicle create(String brand, String model, String vehicleRegistration) {
		Vehicle vehicle = new Vehicle();
		vehicle.setBrand(brand);
		vehicle.setModel(model);
		vehicle.setVehicleRegistration(vehicleRegistration);
		this.getSession().save(vehicle);
		return vehicle;
	}
	
	@Override
	public Vehicle update(Vehicle vehicle) {
		return (Vehicle)this.getSession().merge(vehicle);
	}
	
	@Override
	public void delete(Vehicle vehicle) {
		this.getSession().delete(vehicle);
	}
	
	@Override
	public void addUserVehicle(Vehicle vehicle, UserVehicle userVehicle) {
		vehicle.addOwner(userVehicle);
	}
	
	@Override
	public void removeUserVehicle(Vehicle vehicle, UserVehicle userVehicle) {
		vehicle.removeOwner(userVehicle);
	}
	
	@Override
	public Set<UserVehicle> getUserVehicles(Vehicle vehicle) {
		Query q = this.getSession().createQuery("from UserVehicle a JOIN FETCH a.vehicle WHERE a.vehicle = :vehicle", UserVehicle.class);
		return new HashSet<UserVehicle>(q.setParameter("vehicle", vehicle).getResultList());
	}
}
