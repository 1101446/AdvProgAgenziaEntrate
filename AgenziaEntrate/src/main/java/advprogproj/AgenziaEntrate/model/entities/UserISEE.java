package advprogproj.AgenziaEntrate.model.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_isee")
public class UserISEE implements Serializable{
	
	private User user;
	private ISEE isee;
	
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
	@JoinColumn(name = "ID_ISEE")
	public ISEE getIsee() {
		return this.isee;
	}
	
	public void setIsee(ISEE isee) {
		this.isee = isee;
	}
}
