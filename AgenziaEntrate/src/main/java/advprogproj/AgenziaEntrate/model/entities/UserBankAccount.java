package advprogproj.AgenziaEntrate.model.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_real_estate")
public class UserBankAccount implements Serializable{
	private User user;
	private BankAccount bankAccount;
	
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
	@JoinColumn(name = "ID_BANK_ACCOUNT")
	public BankAccount getBankAccount() {
		return this.bankAccount;
	}
	
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
}
