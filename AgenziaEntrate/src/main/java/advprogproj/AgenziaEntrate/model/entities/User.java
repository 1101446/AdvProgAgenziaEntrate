package advprogproj.AgenziaEntrate.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user")
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
    
    private Set<BankAccount> bankAccounts = new HashSet<BankAccount>();
    
    private Set<UserVehicle> userVehicles = new HashSet<UserVehicle>();
    private Set<UserRealEstate> userRealEstates = new HashSet<UserRealEstate>();
    
    @Id
    @Column(name = "ID_USER")
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
	@JoinColumn(name = "ID_ACCESS")
	public Access getAccess() {
		return access;
	}

	public void setAccess(Access access) {
		this.access = access;
	}
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name= "user_bank_account",
			joinColumns = @JoinColumn(name = "ID_USER"),
			inverseJoinColumns = @JoinColumn(name = "ID_BANK_ACCOUNT")
			)
	public Set<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(Set<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	@OneToMany(mappedBy = "user")
	public Set<UserVehicle> getUserVehicles() {
		return userVehicles;
	}

	public void setUserVehicles(Set<UserVehicle> userVehicles) {
		this.userVehicles = userVehicles;
	}
	
	@OneToMany(mappedBy = "user")
	public Set<UserRealEstate> getUserRealEstates() {
		return userRealEstates;
	}

	public void setUserRealEstates(Set<UserRealEstate> userRealEstates) {
		this.userRealEstates = userRealEstates;
	}
	
	public void addBankAccount(BankAccount bankAccount) {
		this.bankAccounts.add(bankAccount);
		bankAccount.getOwner().add(this);
	}
	
	public void removeBankAccount(BankAccount bankAccount) {
		this.bankAccounts.remove(bankAccount);
		bankAccount.getOwner().remove(this);
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
	
	public void removeRealEstate(UserRealEstate userRealEstate) {
		this.userRealEstates.remove(userRealEstate);
	}
}
