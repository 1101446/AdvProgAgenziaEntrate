package advprogproj.AgenziaEntrate.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "family")
public class Family implements Serializable{
	private long id;
	private User user;
	private String hierarchy;
	private String houseHolder;

	@Id
	@Column(name = "ID_FAMILY")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Id
	@ManyToOne
	@JoinColumn(name = "ID_USER")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name = "HIERARCHY", nullable = false)
	public String getHierarchy() {
		return this.hierarchy;
	}

	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}
	
	@Column(name = "HOUSE_HOLDER", nullable = false)
	public String getHouseHolder() {
		return this.houseHolder;
	}

	public void setHouseHolder(String houseHolder) {
		this.houseHolder = houseHolder;
	}
}
