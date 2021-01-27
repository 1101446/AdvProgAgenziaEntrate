package advprogproj.AgenziaEntrate.model.dao;

import java.util.Date;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface BankAccountDao {
	Session getSession();
	
	public void setSession(Session session);
	
	BankAccount findById(String IBAN);
	
	BankAccount create(String IBAN, String bankName, Date billDate, long balance);
	
	BankAccount update(BankAccount bankAccount);
	
	void delete(BankAccount bankAccount);
}