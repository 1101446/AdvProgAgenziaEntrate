package advprogproj.AgenziaEntrate.services;

import java.util.Date;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import advprogproj.AgenziaEntrate.model.dao.BankAccountDaoDefault;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;

@Service("bankAccount")
public class BankAccountServiceDefault implements BankAccountService{
	
	private BankAccountDaoDefault bankAccountDao;
	
	public BankAccount findById(String IBAN) {
		return this.bankAccountDao.findById(IBAN);
	}
	
	public BankAccount create(String IBAN, String bankName, Date billDate, long balance) {
		return this.bankAccountDao.create(IBAN, bankName, billDate, balance);
	}
	
	public BankAccount update(BankAccount bankAccount) {
		return this.bankAccountDao.update(bankAccount);
	}
	
	public void delete(BankAccount bankAccount) {
		this.delete(bankAccount);
	}
}
