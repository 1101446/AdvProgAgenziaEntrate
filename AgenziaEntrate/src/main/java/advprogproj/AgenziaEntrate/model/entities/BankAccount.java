package advprogproj.AgenziaEntrate.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "BankAccount")
public class BankAccount {
	
	private long IBAN;
	private String bankName;
	private Date billDate;
	private long balance;
	
	@Id
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

}
