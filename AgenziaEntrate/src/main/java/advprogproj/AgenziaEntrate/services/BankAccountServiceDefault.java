package advprogproj.AgenziaEntrate.services;

import java.util.Date;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import advprogproj.AgenziaEntrate.model.dao.BankAccountDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.UserDaoDefault;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;

@Service("bankAccount")
public class BankAccountServiceDefault implements BankAccountService{
	
	private BankAccountDaoDefault bankAccountDao;
	private UserDaoDefault userDao;
	
	@Transactional
	public BankAccount findBankAccount(String bankAccount, Date billDate) {
		return this.bankAccountDao.findById(bankAccount, billDate);
	}
	
	@Transactional
	public BankAccount create(String IBAN, String bankName, Date billDate, long balance) {
		return this.bankAccountDao.create(IBAN, bankName, billDate, balance);
	}
	
	@Transactional
	public BankAccount update(String IBAN, Date billDate) {
		return this.update(this.findBankAccount(IBAN, billDate));
		
	}
	
	@Transactional
	public BankAccount update(BankAccount bankAccount) {
		return this.bankAccountDao.update(bankAccount);
		
	}
	
	@Transactional
	public void delete(String IBAN, Date billDate, String user) {
		this.bankAccountDao.delete(this.findBankAccount(IBAN, billDate));
		this.removeOwner(user, IBAN, billDate);
	}
	
	@Transactional
	public void addOwner(String user, String IBAN, Date billDate) {
		this.bankAccountDao.addOwner(this.userDao.findById(user), this.findBankAccount(IBAN, billDate));
	}
	
	@Transactional
	public void removeOwner(String user, String IBAN, Date billDate) {
		this.bankAccountDao.removeOwner(this.userDao.findById(user), this.findBankAccount(IBAN, billDate));
	}
	
	@Autowired
	public void setBankAccountDao(BankAccountDaoDefault bankAccountDao) {
		this.bankAccountDao = bankAccountDao;
	}
	
	@Autowired
	public void setUserDao(UserDaoDefault userDao) {
		this.userDao = userDao;
	}
	
}