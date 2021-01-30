package advprogproj.AgenziaEntrate.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bank_account")
public class BankAccount implements Serializable{
	private String IBAN;
	private String bankName;
	private Date billDate;
	private long balance;
	
	private Set<User> owners = new HashSet<User>();
	
	@Id
	@Column(name = "ID_BANK_ACCOUNT")
	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}
	
	@Column(name = "BANK_NAME")
	public String getBankName() {
		return bankName;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	@Id
	@Column(name = "BILL_DATE")
	public Date getBillDate() {
		return billDate;
	}
	
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
	@Column(name = "BALANCE")
	public long getBalance() {
		return balance;
	}
	
	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	@ManyToMany(mappedBy = "bankAccounts")
	public Set<User> getOwner() {
		return this.owners;
	}

	public void setOwner(Set<User> owners) {
		this.owners = owners;
	}
	
	public void addOwner(User user) {
		this.owners.add(user);
		user.getBankAccounts().add(this);
	}
	
	public void removeOwner(User user) {
		this.owners.remove(user);
		user.getBankAccounts().remove(this);
	}
		
}
