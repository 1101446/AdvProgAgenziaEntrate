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
@Table(name = "isee")
public class ISEE implements Serializable {
	
	private long id;
	private int yearOfValidity;
	private int valueOfISEE;
	
	private Set<UserISEE> associatedUsers = new HashSet<UserISEE>();
	
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
	
	@OneToMany(mappedBy = "isee")
	public Set<UserISEE> getAssociatedUsers() {
		return this.associatedUsers;
	}

	public void setAssociatedUsers(Set<UserISEE> associatedUsers) {
		this.associatedUsers = associatedUsers;
	}
	
	public void addAssociatedUser(UserISEE userISEE) {
		this.associatedUsers.add(userISEE);
	}
	
	public void removeAssociatedUser(UserISEE userISEE) {
		this.associatedUsers.remove(userISEE);
	}
}
