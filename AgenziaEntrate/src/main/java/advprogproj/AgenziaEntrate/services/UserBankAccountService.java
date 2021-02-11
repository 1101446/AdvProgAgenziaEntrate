package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.Set;

import advprogproj.AgenziaEntrate.model.entities.UserBankAccount;

public interface UserBankAccountService {
	
	public Set<UserBankAccount> findAllUserBankAccounts();
	
	public UserBankAccount findUserBankAccount(String user, String IBAN, LocalDate billDate); 
	
	public UserBankAccount create(String user, String IBAN, LocalDate billDate);
	
	public UserBankAccount update(String user, String IBAN, LocalDate billDate);
	
	public void delete(String user, String IBAN, LocalDate billDate);
}
