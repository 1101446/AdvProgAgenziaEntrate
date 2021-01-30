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
@Table(name = "real_estate")
public class RealEstate implements Serializable{
	private long id;
	private String address;
	private String country;
	private int CAP;
	
	private Set<UserRealEstate> owner = new HashSet<UserRealEstate>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_REAL_ESTATE")
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "ADDRESS", nullable = false)
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "COUNTRY", nullable = false)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name = "CAP", nullable = false)
	public int getCAP() {
		return this.CAP;
	}
	
	public void setCAP(int CAP) {
		this.CAP = CAP;
	}
	
	@OneToMany(mappedBy = "realEstate")
	public Set<UserRealEstate> getOwner() {
		return owner;
	}

	public void setOwner(Set<UserRealEstate> owner) {
		this.owner = owner;
	}
	
	public void addOwner(UserRealEstate userRealEstate) {
		this.owner.add(userRealEstate);
	}
	
	public void removeOwner(UserRealEstate userRealEstate) {
		this.owner.remove(userRealEstate);
	}
	
	public void addUserRealEstate(UserRealEstate userRealEstate) {
		this.addUserRealEstate(userRealEstate);
	}
	
	public void removeUserRealEstate(UserRealEstate userRealEstate) {
		this.removeUserRealEstate(userRealEstate);
	}
}
