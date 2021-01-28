package advprogproj.AgenziaEntrate.model.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_vehicle")
public class UserVehicle {
	private User user;
	private Vehicle vehicle;
	private Date endOfYear;
	private long price;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_USER")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_VEHICLE")
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	@Column
	public Date getEndOfYear() {
		return endOfYear;
	}
	
	public void setEndOfYear(Date endOfYear) {
		this.endOfYear = endOfYear;
	}
	
	@Column
	public long getPrice() {
		return price;
	}
	
	public void setPrice(long price) {
		this.price = price;
	}
}
