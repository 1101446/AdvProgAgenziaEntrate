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
@Table(name = "real_estate")
public class RealEstate {
	
	private long id;
	private String address;
	private String country;
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
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		Country = country;
	}
	
	@Column(name = "cap")
	public long getCAP() {
		return CAP;
	}
	public void setCAP(long CAP) {
		this.CAP = CAP;
	}
	
	@OneToMany(mappedBy = "realEstate")
	public Set<UserRealEstate> getOwner() {
		return owner;
	}

	public void setOwner(Set<UserRealEstate> owner) {
		this.owner = owner;
	}
	
}
