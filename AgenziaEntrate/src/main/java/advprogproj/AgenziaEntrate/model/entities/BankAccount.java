package advprogproj.AgenziaEntrate.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BankAccount")
public class BankAccount {
	
	private long IBAN;
	private String bankName;
	private Date billDate;
	private long balance;
	
	private Set<User> owner = new HashSet<User>();
	
	@Id
	@Column(name = "ID_BANK_ACCOUNT")
	public long getIBAN() {
		return IBAN;
	}
	
	public void setIBAN(long iBAN) {
		IBAN = iBAN;
	}
	
	@Column
	public String getBankName() {
		return bankName;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	@Column
	public Date getBillDate() {
		return billDate;
	}
	
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
	@Column
	public long getBalance() {
		return balance;
	}
	
	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	@ManyToMany(mappedBy = "bankAccounts")
	public Set<User> getOwner() {
		return owner;
	}

	public void setOwner(Set<User> owner) {
		this.owner = owner;
	}
	
	
}
