package advprogproj.AgenziaEntrate.services;

import java.util.Date;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.User;

@Service("bankAccount")
public class BankAccountServiceDefault implements BankAccountService{
	
	public BankAccount findById(String IBAN) {
		return getSession().find(BankAccount.class, IBAN);
	}
	
	public BankAccount create(long IBAN, String bankName, Date billDate, long balance) {
		BankAccount bankAccount= new BankAccount();
		bankAccount.setIBAN(IBAN);
		bankAccount.setBankName(bankName);
		bankAccount.setBillDate(billDate);
		bankAccount.setBalance(balance);
		this.getSession().save(bankAccount);
		return bankAccount;
	}
	
	public BankAccount update(BankAccount bankAccount) {
		return (BankAccount)this.getSession().merge(bankAccount);
	}
	
	public void delete(BankAccount bankAccount) {
		this.getSession().delete(bankAccount);
	}
}
