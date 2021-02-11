package advprogproj.AgenziaEntrate.model.dao;

import java.util.Set;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.UserBankAccount;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface UserBankAccountDao {
	Session getSession();
	
	public void setSession(Session session);
	
	Set<UserBankAccount> findAll();
	
	UserBankAccount findById(User user, BankAccount bankAccount);
	
	UserBankAccount create(User user, BankAccount bankAccount);
	
	UserBankAccount update(UserBankAccount userRealEstate);
	
	void delete(UserBankAccount userRealEstate);
}
