package advprogproj.AgenziaEntrate.services;

import java.util.Date;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.dao.AccessDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.BankAccountDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.UserDaoDefault;

@Service("userService")
public class UserServiceDefault implements UserService, UserDetailsService{
	
	private UserDaoDefault userDao;
	private BankAccountDaoDefault bankAccountDao;
	private AccessDaoDefault accessDao;
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		User user = userDao.findByEmail(email);
		UserBuilder builder = null;
		if(user != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(email);
			builder.disabled(false);
			builder.password(user.getPassword());
			String role = user.getAccess().getRoleName();
			builder.roles(role);
		}else
			throw new UsernameNotFoundException("user not found");
		return builder.build();
	} 
	
	@Transactional
	public User findUser(String user) {
		return this.userDao.findById(user);
	}
	
	@Transactional
	public User create(String cf, String firstName, String secondName, Date birthDate, String email, String password, boolean handicap, long access) {
		return this.userDao.create(cf, firstName, secondName, birthDate, email, password, handicap, this.accessDao.findById(access));
	}
	
	@Transactional
	public User update(String user){
		return this.update(this.findUser(user));
	}
	
	@Transactional
	public User update(User user) {
		return this.userDao.update(user);
	}
	
	@Transactional
	public void delete(String user, String bankAccount) {
		this.removeBankAccount(user, bankAccount);		
		this.userDao.delete(this.findUser(user));
	}
	
	@Transactional
	public void addBankAccount(String user, String bankAccount) {
		this.userDao.addBankAccount(this.findUser(user), this.bankAccountDao.findById(bankAccount));
	}
	
	@Transactional
	public void removeBankAccount(String user, String bankAccount) {
		this.userDao.removeBankAccount(this.findUser(user), this.bankAccountDao.findById(bankAccount));
	}
	
	@Autowired
	public void setUserDao(UserDaoDefault userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setBankAccountDao(BankAccountDaoDefault bankAccountDao) {
		this.bankAccountDao = bankAccountDao;
	}
	
	@Autowired
	public void setAccessDao(AccessDaoDefault accessDao) {
		this.accessDao = accessDao;
	}
}
