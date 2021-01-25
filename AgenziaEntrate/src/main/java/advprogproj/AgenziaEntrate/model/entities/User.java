package advprogproj.AgenziaEntrate.model.entities;

import java.util.Date;

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
@Table(name = "User")
public class User
{
    private String cf;
    private String firstName;
    private String secondName;
    private Date birthD;
    private String email;
    private String password;
    private boolean handicap;
    
    private Access access;
    
    @Id
    @Column(name = "USER_ID")
    public String getCf() {
		return cf;
	}
	
	public void setCf(String cf) {
		this.cf = cf;
	}
	
	@Column
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column
	public String getSecondName() {
		return secondName;
	}
	
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	@Column( name = "BIRTH_DATE")
	public Date getBirthD() {
		return birthD;
	}
	
	public void setBirthD(Date birthD) {
		this.birthD = birthD;
	}
	
	@Column
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column
	public boolean isHandicap() {
		return handicap;
	}
	
	public void setHandicap(boolean handicap) {
		this.handicap = handicap;
	}
	
	@ManyToOne
	@JoinColumn(name = "ACCESS_ID")
	public Access getAccess() {
		return access;
	}

	public void setAccess(Access access) {
		this.access = access;
	}

}
