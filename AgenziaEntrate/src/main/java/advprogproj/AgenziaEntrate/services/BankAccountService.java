package advprogproj.AgenziaEntrate.services;

import java.util.Date;

import advprogproj.AgenziaEntrate.model.entities.BankAccount;

public interface BankAccountService {
	
	public BankAccount findBankAccount(String bankAccount, Date billDate);
	
	public BankAccount create(String IBAN, String bankName, Date billDate, long balance);

	public BankAccount update(String IBAN, Date billDate);
	
	public BankAccount update(BankAccount bankAccount);

	public void delete(String IBAN, Date billDate, String user);
	
	public void addOwner(String user, String IBAN, Date billDate) ;
	
	public void removeOwner(String user, String IBAN, Date billDate);
}
