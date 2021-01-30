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
	
	private Set<UserVehicle> owner = new HashSet<UserVehicle>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_VEHICLE")
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(nullable = false)
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Column(nullable = false)
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	@Column(nullable = false)
	public String getVehicleRegistration() {
		return vehicleRegistration;
	}
	
	public void setVehicleRegistration(String vehicleRegistration) {
		this.vehicleRegistration = vehicleRegistration;
	}
	
	@OneToMany(mappedBy = "vehicle")
	public Set<UserVehicle> getOwner() {
		return owner;
	}

	public void setOwner(Set<UserVehicle> owner) {
		this.owner = owner;
	}
	
	public void addOwner(UserVehicle userVehicle) {
		this.owner.add(userVehicle);
	}
	
	public void removeOwner(UserVehicle userVehicle) {
		this.owner.remove(userVehicle);
	}
	
}
