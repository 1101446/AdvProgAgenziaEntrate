package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import advprogproj.AgenziaEntrate.model.dao.BankAccountDao;
import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.User;

@Service("bankAccount")
public class BankAccountServiceDefault implements BankAccountService{
	
	private BankAccountDao bankAccountDao;
	private UserDao userDao;
	
	@Transactional
	@Override
	public List<BankAccount> findAllUserBankAccounts() {
		return this.bankAccountDao.findAllWithUser();
	}
	
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
	public BankAccount update(String IBAN, String billDate) {
		return this.update(this.findBankAccount(IBAN, LocalDate.parse(billDate)));
		
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
		for(User u : this.bankAccountDao.getOwners(bk)) {
			u.getBankAccounts().removeIf(b -> b.getIBAN() == bk.getIBAN() && b.getBillDate() == bk.getBillDate());
			this.userDao.update(u);
		}
		this.bankAccountDao.delete(bk);
	}
	
	@Transactional
	@Override
	public void addOwner(String user, String IBAN, String billDate) {
		BankAccount bk = this.findBankAccount(IBAN, LocalDate.parse(billDate));
		User u = this.userDao.findById(user);
		this.bankAccountDao.addOwner(u, bk);
		this.userDao.update(u);
		this.bankAccountDao.update(bk);
	}
	
	@Transactional
	@Override
	public void removeOwner(String user, String IBAN, String billDate) {
		BankAccount bk = this.findBankAccount(IBAN, LocalDate.parse(billDate));
		User u = this.userDao.findById(user);
		this.bankAccountDao.removeOwner(u, bk);
		this.userDao.update(u);
		this.bankAccountDao.update(bk);
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