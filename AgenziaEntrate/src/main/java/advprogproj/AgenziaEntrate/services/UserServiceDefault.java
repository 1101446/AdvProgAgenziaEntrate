package advprogproj.AgenziaEntrate.services;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.dao.AccessDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.BankAccountDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.UserDaoDefault;

@Service("userService")
public class UserServiceDefault implements UserService{
	
	private UserDaoDefault userDao;
	private BankAccountDaoDefault bankAccountDao;
	private AccessDaoDefault accessDao;
	
	@Transactional
	public User findById(String user) {
		return this.userDao.findById(user);
	}
	
	@Transactional
	public User create(String cf, String firstName, String secondName, Date birthDate, String email, String password, boolean handicap, long access) {
		return this.userDao.create(cf, firstName, secondName, birthDate, email, password, handicap, this.accessDao.findById(access));
	}
	
	@Transactional
	public User update(String user){
		return this.update(this.findById(user));
	}
	
	@Transactional
	public User update(User user) {
		return this.userDao.update(user);
	}
	
	@Transactional
	public void delete(String user, String bankAccount) {
		this.removeBankAccount(user, bankAccount);		
		this.userDao.delete(this.findById(user));
	}
	
	@Transactional
	public void addBankAccount(String user, String bankAccount) {
		this.userDao.addBankAccount(this.findById(user), this.bankAccountDao.findById(bankAccount));
	}
	
	@Transactional
	public void removeBankAccount(String user, String bankAccount) {
		this.userDao.removeBankAccount(this.findById(user), this.bankAccountDao.findById(bankAccount));
	}

	
}
