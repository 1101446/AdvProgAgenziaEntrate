package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.dao.AccessDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.BankAccountDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.ISEEDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.UserDaoDefault;

@Service("userService")
public class UserServiceDefault implements UserService, UserDetailsService{
	
	@Autowired
	private UserDaoDefault userDao;
	
	private BankAccountDaoDefault bankAccountDao;
	private AccessDaoDefault accessDao;
	private ISEEDaoDefault iseeDao;
	
	@Transactional
	public List<User> findAllUsers() {
		return this.userDao.findAll();
	}
	
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
			throw new UsernameNotFoundException("User not found");
		return builder.build();
	} 
	
	@Transactional
	public User findUser(String user) {
		return this.userDao.findById(user);
	}
	
	@Transactional
	public User create(String cf, String firstName, String secondName, LocalDate birthDate, String email, String password, boolean handicap, long access) {
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
	public void delete(String user, String bankAccount, String billDate) {
		this.removeBankAccount(user, bankAccount, billDate);		
		this.userDao.delete(this.findUser(user));
	}
	
	@Transactional
	public void addBankAccount(String user, String IBAN, String billDate) {
		this.userDao.addBankAccount(this.findUser(user), this.bankAccountDao.findById(IBAN, LocalDate.parse(billDate)));
	}
	
	@Transactional
	public void removeBankAccount(String user, String IBAN, String billDate) {
		this.userDao.removeBankAccount(this.findUser(user), this.bankAccountDao.findById(IBAN, LocalDate.parse(billDate)));
	}
	
	@Transactional
	public void addISEE(String user, long isee, String billDate) {
		this.userDao.addAssociatedISEE(this.findUser(user), this.iseeDao.findById(isee));
	}
	
	@Transactional
	public void removeISEE(String user, long isee) {
		this.userDao.removeAssociatedISEE(this.findUser(user), this.iseeDao.findById(isee));
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
	
	@Autowired
	public void setISEEDao(ISEEDaoDefault iseeDao) {
		this.iseeDao = iseeDao;
	}
}
