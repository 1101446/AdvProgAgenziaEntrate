package advprogproj.AgenziaEntrate.model.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface BankAccountDao {
	Session getSession();
	
	public void setSession(Session session);
	
	List<BankAccount> findAllWithUser();
	
	List<BankAccount> findAll();
	
	BankAccount findById(String IBAN, LocalDate billDate);
	
	Set<BankAccount> findByIBAN(String IBAN);
	
	BankAccount create(String IBAN, String bankName, LocalDate billDate, long balance);
	
	BankAccount update(BankAccount bankAccount);
	
	void delete(BankAccount bankAccount);
	
	void addOwner(User user, BankAccount bankAccount);
	
	void removeOwner(User user, BankAccount bankAccount);
	
	Set<User> getOwners(BankAccount bankAccount);
}
