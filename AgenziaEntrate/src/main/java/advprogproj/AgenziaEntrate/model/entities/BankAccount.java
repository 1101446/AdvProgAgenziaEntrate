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
@Table(name = "bank_account")
public class BankAccount {
	
	private String IBAN;
	private String bankName;
	private Date billDate;
	private long balance;
	
	private Set<User> owner = new HashSet<User>();
	
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
		return owner;
	}

	public void setOwner(Set<User> owner) {
		this.owner = owner;
	}
		
}
