package advprogproj.AgenziaEntrate.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable{
    private String cf;
    private String firstName;
    private String secondName;
    private LocalDate birthD;
    private String email;
    private String password;
    private boolean handicap;
    
    private Access access;
    //private Family family;
    
    private Set<UserBankAccount> bankAccounts = new HashSet<UserBankAccount>();
    private Set<UserISEE> associatedISEEs = new HashSet<UserISEE>();
    
    private Set<UserVehicle> userVehicles = new HashSet<UserVehicle>();
    private Set<UserRealEstate> userRealEstates = new HashSet<UserRealEstate>();
    
    @Id
    @Column(name = "ID_USER")
    public String getCf() {
		return this.cf;
	}
	
	public void setCf(String cf) {
		this.cf = cf;
	}
	
	@Column(nullable = false)
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(nullable = false)
	public String getSecondName() {
		return this.secondName;
	}
	
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	@Column( name = "BIRTH_DATE")
	public LocalDate getBirthD() {
		return this.birthD;
	}
	
	public void setBirthD(LocalDate birthD) {
		this.birthD = birthD;
	}
	
	@Column(unique = true, nullable = false)
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(nullable = false)
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "HANDICAP")
	public boolean isHandicap() {
		return this.handicap;
	}
	
	public void setHandicap(boolean handicap) {
		this.handicap = handicap;
	}
	
	@ManyToOne
	@JoinColumn(name = "ID_ACCESS")
	public Access getAccess() {
		return this.access;
	}

	public void setAccess(Access access) {
		this.access = access;
	}
	
	@OneToMany(mappedBy = "user")
	public Set<UserBankAccount> getBankAccounts() {
		return this.bankAccounts;
	}

	public void setBankAccounts(Set<UserBankAccount> userBankAccounts) {
		this.bankAccounts = userBankAccounts;
	}
	
	@OneToMany(mappedBy = "user")
	public Set<UserISEE> getAssociatedISEEs() {
		return this.associatedISEEs;
	}

	public void setAssociatedISEEs(Set<UserISEE> associatedISEEs) {
		this.associatedISEEs = associatedISEEs;
	}

	@OneToMany(mappedBy = "user")
	public Set<UserVehicle> getUserVehicles() {
		return this.userVehicles;
	}

	public void setUserVehicles(Set<UserVehicle> userVehicles) {
		this.userVehicles = userVehicles;
	}
	
	@OneToMany(mappedBy = "user")
	public Set<UserRealEstate> getUserRealEstates() {
		return this.userRealEstates;
	}

	public void setUserRealEstates(Set<UserRealEstate> userRealEstates) {
		this.userRealEstates = userRealEstates;
	}
	
	public void addBankAccount(UserBankAccount bankAccount) {
		this.bankAccounts.add(bankAccount);
	}
	
	public void removeBankAccount(UserBankAccount userBankAccount) {
		this.bankAccounts.remove(userBankAccount);
	}
	
	public void addAssociatedISEE(UserISEE userISEE) {
		this.associatedISEEs.add(userISEE);
	}
	
	public void removeAssociatedISEE(UserISEE userISEE) {
		this.associatedISEEs.remove(userISEE);
	}
	
	public void addUserVehicle(UserVehicle userVehicle) {
		this.userVehicles.add(userVehicle);
	}
	
	public void removeUserVehicle(UserVehicle userVehicle) {
		this.userVehicles.remove(userVehicle);
	}
	
	public void addUserRealEstate(UserRealEstate userRealEstate) {
		this.userRealEstates.add(userRealEstate);
	}
	
	public void removeUserRealEstate(UserRealEstate userRealEstate) {
		this.userRealEstates.remove(userRealEstate);
	}
}
