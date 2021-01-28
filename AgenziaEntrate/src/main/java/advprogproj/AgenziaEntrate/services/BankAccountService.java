package advprogproj.AgenziaEntrate.services;

import java.util.Date;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface BankAccountService {
	
	public BankAccount findById(String IBAN);
	
	public BankAccount create(String IBAN, String bankName, Date billDate, long balance);
	
	public BankAccount update(BankAccount bankAccount);
	
	public void delete(BankAccount bankAccount);
}
