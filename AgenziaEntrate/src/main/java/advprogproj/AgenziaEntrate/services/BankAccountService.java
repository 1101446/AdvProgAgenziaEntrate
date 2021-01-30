package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;

import advprogproj.AgenziaEntrate.model.entities.BankAccount;

public interface BankAccountService {
	
	public BankAccount findBankAccount(String bankAccount, LocalDate billDate);
	
	public BankAccount create(String IBAN, String bankName, LocalDate billDate, long balance);

	public BankAccount update(String IBAN, LocalDate billDate);
	
	public BankAccount update(BankAccount bankAccount);

	public void delete(String IBAN, LocalDate billDate, String user);
	
	public void addOwner(String user, String IBAN, LocalDate billDate) ;
	
	public void removeOwner(String user, String IBAN, LocalDate billDate);
}
