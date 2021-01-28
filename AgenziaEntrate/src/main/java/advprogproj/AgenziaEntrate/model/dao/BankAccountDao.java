package advprogproj.AgenziaEntrate.model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;

public interface BankAccountDao {
	Session getSession();
	
	public void setSession(Session session);
	
	List<BankAccount> findAll();
	
	BankAccount findById(String IBAN);
	
	BankAccount create(String IBAN, String bankName, Date billDate, long balance);
	
	BankAccount update(BankAccount bankAccount);
	
	void delete(BankAccount bankAccount);
}
