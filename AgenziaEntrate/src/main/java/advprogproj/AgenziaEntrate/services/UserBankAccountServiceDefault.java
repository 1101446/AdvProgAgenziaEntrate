package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.UserBankAccount;
import advprogproj.AgenziaEntrate.model.dao.BankAccountDao;
import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.dao.UserBankAccountDao;

@Service("userBankAccount")
public class UserBankAccountServiceDefault implements UserBankAccountService{
	
	private UserBankAccountDao userBankAccountDao;
	private UserDao userDao;
	private BankAccountDao bankAccountDao;
	
	@Transactional
	@Override
	public Set<UserBankAccount> findAllUserBankAccounts() {
		return this.userBankAccountDao.findAll();
	}
	
	@Transactional
	@Override
	public UserBankAccount findUserBankAccount(String user, String IBAN, LocalDate billDate) {
		return this.userBankAccountDao.findById(this.userDao.findById(user), this.bankAccountDao.findById(IBAN,billDate));
	}
	
	@Transactional
	@Override
	public UserBankAccount create(String user, String IBAN, LocalDate billDate) {
		User u = this.userDao.findById(user);
		BankAccount bk = this.bankAccountDao.findById(IBAN, billDate);
		UserBankAccount newUserBankAccount = this.userBankAccountDao.create(u, bk);
		return newUserBankAccount;
	}
	
	@Transactional
	@Override
	public UserBankAccount update(String user, String IBAN, LocalDate billDate) {
		UserBankAccount ubk = new UserBankAccount();
		User u = this.userDao.findById(user);
		BankAccount bk = this.bankAccountDao.findById(IBAN, billDate);
		ubk.setUser(u);
		ubk.setBankAccount(bk);
		this.userBankAccountDao.update(ubk);
		this.userDao.update(ubk.getUser());
		this.bankAccountDao.update(ubk.getBankAccount());
		return ubk;
	}
	
	@Transactional
	@Override
	public void delete(String user, String IBAN, LocalDate billDate) {
		UserBankAccount ubk = this.findUserBankAccount(user, IBAN, billDate);
		this.userDao.removeBankAccount(ubk.getUser(), ubk);
		this.bankAccountDao.removeOwner(ubk.getBankAccount(), ubk);
		this.userBankAccountDao.delete(ubk);
	}
	
	@Autowired
	public void setUserRealEstateDao(UserBankAccountDao userBankAccountDao) {
		this.userBankAccountDao = userBankAccountDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setBankAccountDao(BankAccountDao bankAccountDao) {
		this.bankAccountDao = bankAccountDao;
	}
}
