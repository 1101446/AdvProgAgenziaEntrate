package advprogproj.AgenziaEntrate.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "RealEstate")
public class RealEstate {
	
	private long id;
	private String Address;
	private long CAP;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Column
	public String getAddress() {
		return Address;
	}
	
	public void setAddress(String address) {
		Address = address;
	}
	
	@Column
	public long getCAP() {
		return CAP;
	}
	public void setCAP(long cAP) {
		CAP = cAP;
	}
	
	
}
