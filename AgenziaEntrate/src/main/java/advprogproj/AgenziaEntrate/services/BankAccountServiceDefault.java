package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import advprogproj.AgenziaEntrate.model.dao.BankAccountDao;
import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;

@Service("bankAccount")
public class BankAccountServiceDefault implements BankAccountService{
	
	private BankAccountDao bankAccountDao;
	private UserDao userDao;
	
	@Transactional
	@Override
	public List<BankAccount> findAllBankAccounts() {
		return this.bankAccountDao.findAll();
	}
	
	@Transactional
	@Override
	public BankAccount findBankAccount(String bankAccount, String billDate) {
		return this.bankAccountDao.findById(bankAccount, LocalDate.parse(billDate));
	}
	
	@Transactional
	@Override
	public BankAccount create(String IBAN, String bankName, LocalDate billDate, long balance) {
		return this.bankAccountDao.create(IBAN, bankName, billDate, balance);
	}
	
	@Transactional
	@Override
	public BankAccount update(String IBAN, String billDate) {
		return this.update(this.findBankAccount(IBAN, billDate));
		
	}
	
	@Transactional
	@Override
	public BankAccount update(BankAccount bankAccount) {
		return this.bankAccountDao.update(bankAccount);
		
	}
	
	@Transactional
	@Override
	public void delete(String IBAN, String billDate, String user) {
		this.bankAccountDao.delete(this.findBankAccount(IBAN, billDate));
		this.removeOwner(user, IBAN, billDate);
	}
	
	@Transactional
	@Override
	public void addOwner(String user, String IBAN, String billDate) {
		this.bankAccountDao.addOwner(this.userDao.findById(user), this.findBankAccount(IBAN, billDate));
	}
	
	@Transactional
	@Override
	public void removeOwner(String user, String IBAN, String billDate) {
		this.bankAccountDao.removeOwner(this.userDao.findById(user), this.findBankAccount(IBAN, billDate));
	}
	
	@Autowired
	public void setBankAccountDao(BankAccountDao bankAccountDao) {
		this.bankAccountDao = bankAccountDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}