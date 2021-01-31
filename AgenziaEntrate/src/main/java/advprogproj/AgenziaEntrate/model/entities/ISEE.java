package advprogproj.AgenziaEntrate.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "isee")
public class ISEE {
	
	private long id;
	private int yearOfValidity;
	private int valueOfISEE;
	
	private Set<User> associatedUsers = new HashSet<User>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ISEE")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column
	public int getYearOfValidity() {
		return this.yearOfValidity;
	}

	public void setYearOfValidity(int yearOfValidity) {
		this.yearOfValidity = yearOfValidity;
	}
	
	@Column
	public int getValueOfISEE() {
		return this.valueOfISEE;
	}

	public void setValueOfISEE(int valueOfISEE) {
		this.valueOfISEE = valueOfISEE;
	}
	
	@ManyToMany(mappedBy = "associatedISEEs")
	public Set<User> getAssociatedUsers() {
		return this.associatedUsers;
	}

	public void setAssociatedUsers(Set<User> associatedUsers) {
		this.associatedUsers = associatedUsers;
	}
}
