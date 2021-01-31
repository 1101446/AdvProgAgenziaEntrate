package advprogproj.AgenziaEntrate.model.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_real_estate")
public class UserRealEstate implements Serializable{
	private User user;
	private RealEstate realEstate;
	private LocalDate endOfYear;
	private long price;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_USER")
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Id
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "ID_REAL_ESTATE")
	public RealEstate getRealEstate() {
		return this.realEstate;
	}
	
	public void setRealEstate(RealEstate realEstate) {
		this.realEstate = realEstate;
	}
	
	@Id
	public LocalDate getEndOfYear() {
		return this.endOfYear;
	}
	
	public void setEndOfYear(LocalDate endOfYear) {
		this.endOfYear = endOfYear;
	}
	
	@Column(nullable = false)
	public long getPrice() {
		return this.price;
	}
	
	public void setPrice(long price) {
		this.price = price;
	}
}
