package advprogproj.AgenziaEntrate.model.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable{
	private long id;
	private String brand;
	private String model;
	private String vehicleRegistration;
	
	private Set<UserVehicle> owners = new HashSet<UserVehicle>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VEHICLE")
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(nullable = false)
	public String getBrand() {
		return this.brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Column(nullable = false)
	public String getModel() {
		return this.model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	@Column(nullable = false)
	public String getVehicleRegistration() {
		return this.vehicleRegistration;
	}
	
	public void setVehicleRegistration(String vehicleRegistration) {
		this.vehicleRegistration = vehicleRegistration;
	}
	
	@OneToMany(mappedBy = "vehicle")
	public Set<UserVehicle> getOwners() {
		return this.owners;
	}

	public void setOwner(Set<UserVehicle> owners) {
		this.owners = owners;
	}
	
	public void addOwner(UserVehicle userVehicle) {
		this.owners.add(userVehicle);
	}
	
	public void removeOwner(UserVehicle userVehicle) {
		this.owners.remove(userVehicle);
	}
	
}
