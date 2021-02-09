package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.List;

import advprogproj.AgenziaEntrate.model.entities.BankAccount;

public interface BankAccountService {
	
	public List<BankAccount> findAllUserBankAccounts();
	
	public List<BankAccount> findAllBankAccounts();
	
	public BankAccount findBankAccount(String bankAccount, LocalDate billDate);
	
	public BankAccount create(String IBAN, String bankName, LocalDate billDate, long balance);

	public BankAccount update(String IBAN, String billDate);
	
	public BankAccount update(BankAccount bankAccount);

	public void delete(String IBAN, String billDate);
	
	public void addOwner(String user, String IBAN, String billDate) ;
	
	public void removeOwner(String user, String IBAN, String billDate);
}
