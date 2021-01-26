package advprogproj.AgenziaEntrate.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "family")
public class Family{
	private long id;
	private User user;
	private String hierarchy;
	private String houseHolder;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FAMILY")
	public long getId() {
		return id;
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

	@Column
	public String getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}
	
	public String getHouseHolder() {
		return houseHolder;
	}

	public void setHouseHolder(String houseHolder) {
		this.houseHolder = houseHolder;
	}
}
