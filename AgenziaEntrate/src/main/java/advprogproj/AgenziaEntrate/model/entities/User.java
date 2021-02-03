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
    
    private Set<BankAccount> bankAccounts = new HashSet<BankAccount>();
    private Set<ISEE> associatedISEEs = new HashSet<ISEE>();
    
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
	
	/*@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	public Family getFamily() {
		return this.family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}*/
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
	@JoinTable(
			name= "user_bank_account",
			joinColumns = @JoinColumn(name = "ID_USER"),
			inverseJoinColumns = {@JoinColumn(name = "ID_BANK_ACCOUNT"),@JoinColumn(name = "BILL_DATE")}
			)
	public Set<BankAccount> getBankAccounts() {
		return this.bankAccounts;
	}

	public void setBankAccounts(Set<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
	@JoinTable(
			name= "user_isee",
			joinColumns = @JoinColumn(name = "ID_USER"),
			inverseJoinColumns = @JoinColumn(name = "ID_ISEE")
			)
	public Set<ISEE> getAssociatedISEEs() {
		return this.associatedISEEs;
	}

	public void setAssociatedISEEs(Set<ISEE> associatedISEEs) {
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
	
	public void addBankAccount(BankAccount bankAccount) {
		this.bankAccounts.add(bankAccount);
		bankAccount.getOwners().add(this);
	}
	
	public void removeBankAccount(BankAccount bankAccount) {
		this.bankAccounts.remove(bankAccount);
		bankAccount.getOwners().remove(this);
	}
	
	public void addAssociatedISEE(ISEE isee) {
		this.associatedISEEs.add(isee);
		isee.getAssociatedUsers().add(this);
	}
	
	public void removeAssociatedISEE(ISEE isee) {
		this.associatedISEEs.remove(isee);
		isee.getAssociatedUsers().remove(this);
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
