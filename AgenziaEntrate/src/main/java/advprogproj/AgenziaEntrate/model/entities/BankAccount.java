package advprogproj.AgenziaEntrate.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bank_account")
public class BankAccount implements Serializable{
	private String IBAN;
	private LocalDate billDate;
	private String bankName;
	private long balance;
	
	private Set<UserBankAccount> owners = new HashSet<UserBankAccount>();
	
	@Id
	@Column(name = "ID_BANK_ACCOUNT")
	public String getIBAN() {
		return this.IBAN;
	}

	public void setIBAN(String iBAN) {
		this.IBAN = iBAN;
	}
	
	@Id
	@Column(name = "BILL_DATE")
	public LocalDate getBillDate() {
		return this.billDate;
	}
	
	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}
	
	@Column(name = "BANK_NAME", nullable = false)
	public String getBankName() {
		return this.bankName;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	@Column(name = "BALANCE", nullable = false)
	public long getBalance() {
		return this.balance;
	}
	
	public void setBalance(long balance) {
		this.balance = balance;
	}
	
	@OneToMany(mappedBy = "bankAccount")
	public Set<UserBankAccount> getOwners() {
		return this.owners;
	}

	public void setOwners(Set<UserBankAccount> owners) {
		this.owners = owners;
	}
	
	public void addOwner(UserBankAccount userBankAccont) {
		this.owners.add(userBankAccont);
	}
	
	public void removeOwner(UserBankAccount userBankAccont) {
		this.owners.remove(userBankAccont);
	}
}
