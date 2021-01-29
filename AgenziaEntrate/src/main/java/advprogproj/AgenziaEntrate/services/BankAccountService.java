package advprogproj.AgenziaEntrate.services;

import java.util.Date;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface BankAccountService {
	
	public BankAccount findBankAccount(String bankAccount);
	
	public User findUser(String user);
	
	public BankAccount create(String IBAN, String bankName, Date billDate, long balance);
	
	public BankAccount update(String bankAccount);
	
	public BankAccount update(BankAccount bankAccount);
	
	public void delete(String bankAccount, String user);
	
	public void addOwner(String user, String bankAccount) ;
	
	public void removeOwner(String user, String bankAccount);
}
