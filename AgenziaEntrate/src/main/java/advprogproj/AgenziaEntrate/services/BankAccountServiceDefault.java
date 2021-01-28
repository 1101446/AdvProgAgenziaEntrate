package advprogproj.AgenziaEntrate.services;

import java.util.Date;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import advprogproj.AgenziaEntrate.model.dao.BankAccountDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.UserDaoDefault;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.User;

@Service("bankAccount")
public class BankAccountServiceDefault implements BankAccountService{
	
	private BankAccountDaoDefault bankAccountDao;
	private UserDaoDefault userDao;
	
	@Transactional
	public BankAccount findById(String bankAccount) {
		return this.bankAccountDao.findById(bankAccount);
	}
	
	@Transactional
	public BankAccount create(String IBAN, String bankName, Date billDate, long balance) {
		return this.bankAccountDao.create(IBAN, bankName, billDate, balance);
	}
	
	@Transactional
	public BankAccount update(String bankAccount) {
		return this.update(bankAccountDao.findById(bankAccount));
		
	}
	
	@Transactional
	public BankAccount update(BankAccount bankAccount) {
		return this.bankAccountDao.update(bankAccount);
		
	}
	
	@Transactional
	public void delete(String bankAccount, String user) {
		this.bankAccountDao.delete(this.bankAccountDao.findById(bankAccount));
		this.removeOwner(user, bankAccount);
	}
	
	@Transactional
	public void addOwner(String user, String bankAccount) {
		this.bankAccountDao.addOwner(this.userDao.findById(user), this.findById(bankAccount));
	}
	
	@Transactional
	public void removeOwner(String user, String bankAccount) {
		this.bankAccountDao.removeOwner(this.userDao.findById(user), this.findById(bankAccount));
	}
	
}
