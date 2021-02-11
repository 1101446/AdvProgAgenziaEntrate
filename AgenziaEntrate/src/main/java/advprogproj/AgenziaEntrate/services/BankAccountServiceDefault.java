package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import advprogproj.AgenziaEntrate.model.dao.BankAccountDao;
import advprogproj.AgenziaEntrate.model.dao.UserBankAccountDao;
import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.UserBankAccount;

@Service("bankAccount")
public class BankAccountServiceDefault implements BankAccountService{
	
	private UserBankAccountDao userBankAccountDao;
	private BankAccountDao bankAccountDao;
	private UserDao userDao;
	
	@Transactional
	@Override
	public List<BankAccount> findAllBankAccounts() {
		return this.bankAccountDao.findAll();
	}
	
	@Transactional
	@Override
	public BankAccount findBankAccount(String bankAccount, LocalDate billDate) {
		return this.bankAccountDao.findById(bankAccount, billDate);
	}
	
	@Transactional
	@Override
	public BankAccount create(String IBAN, String bankName, LocalDate billDate, long balance) {
		return this.bankAccountDao.create(IBAN, bankName, billDate, balance);
	}
	
	@Transactional
	@Override
	public BankAccount update(BankAccount bankAccount) {
		return this.bankAccountDao.update(bankAccount);
		
	}
	
	@Transactional
	@Override
	public void delete(String IBAN, String billDate) {
		BankAccount bk = this.findBankAccount(IBAN, LocalDate.parse(billDate));
		for(UserBankAccount ubk : this.bankAccountDao.getOwners(bk)) {
			this.userDao.removeBankAccount(ubk.getUser(), ubk);
			this.bankAccountDao.removeOwner(bk, ubk);
			this.userBankAccountDao.delete(ubk);
		}
		this.bankAccountDao.delete(bk);
	}
	
	@Autowired
	public void setUserBankAccountDao(UserBankAccountDao userBankAccountDao) {
		this.userBankAccountDao = userBankAccountDao;
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