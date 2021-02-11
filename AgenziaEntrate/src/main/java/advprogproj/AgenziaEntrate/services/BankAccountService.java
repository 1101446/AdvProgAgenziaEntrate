package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.List;

import advprogproj.AgenziaEntrate.model.entities.BankAccount;

public interface BankAccountService {
	
	public List<BankAccount> findAllBankAccounts();
	
	public BankAccount findBankAccount(String bankAccount, LocalDate billDate);
	
	public BankAccount create(String IBAN, String bankName, LocalDate billDate, long balance);
	
	public BankAccount update(BankAccount bankAccount);

	public void delete(String IBAN, String billDate);
}
