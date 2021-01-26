package advprogproj.AgenziaEntrate.model.entities;

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
@Table(name = "RealEstate")
public class RealEstate {
	
	private long id;
	private String Address;
	private String Country;
	private long CAP;
	
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
	
	@Column
	public String getAddress() {
		return Address;
	}
	
	public void setAddress(String address) {
		Address = address;
	}
	
	@Column
	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}
	
	@Column
	public long getCAP() {
		return CAP;
	}
	public void setCAP(long cAP) {
		CAP = cAP;
	}
	
	@OneToMany(mappedBy = "realEstate")
	public Set<UserRealEstate> getOwner() {
		return owner;
	}

	public void setOwner(Set<UserRealEstate> owner) {
		this.owner = owner;
	}
	
	
}
