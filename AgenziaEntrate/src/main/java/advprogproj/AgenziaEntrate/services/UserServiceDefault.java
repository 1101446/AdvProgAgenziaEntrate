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
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.Family;
import advprogproj.AgenziaEntrate.model.entities.ISEE;
import advprogproj.AgenziaEntrate.model.dao.AccessDao;
import advprogproj.AgenziaEntrate.model.dao.BankAccountDao;
import advprogproj.AgenziaEntrate.model.dao.FamilyDao;
import advprogproj.AgenziaEntrate.model.dao.ISEEDao;
import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.dao.UserRealEstateDao;
import advprogproj.AgenziaEntrate.model.dao.UserVehicleDao;

@Service("userService")
public class UserServiceDefault implements UserService, UserDetailsService{
	
	@Autowired
	private UserDao userDao;
	
	private BankAccountDao bankAccountDao;
	private AccessDao accessDao;
	private ISEEDao iseeDao;
	private FamilyDao familyDao;
	private UserRealEstateDao userRealEstateDao;
	private UserVehicleDao userVehicleDao;
	
	@Transactional
	@Override
	public List<User> findAllUsers() {
		return this.userDao.findAll();
	}
	
	@Transactional(readOnly = true)
	@Override
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
	@Override
	public User findUser(String user) {
		return this.userDao.findById(user);
	}
	
	@Transactional
	@Override
	public User findUserEmail(String email) {
		return this.userDao.findByEmail(email);
	}
	
	@Transactional
	@Override
	public User create(String cf, String firstName, String secondName, LocalDate birthDate, String email, String password, boolean handicap, long access) {
		return this.userDao.create(cf, firstName, secondName, birthDate, email, password, handicap, this.accessDao.findById(access));
	}
	
	@Transactional
	@Override
	public User update(User user) {
		return this.userDao.update(user);
	}

	@Transactional
	@Override
	public void delete(String user) {
		User u = this.userDao.findById(user);
		
		for(BankAccount a : u.getBankAccounts()) {
			a.getOwners().removeIf(us -> us == u);
		}
		
		for(ISEE i : u.getAssociatedISEEs()) {
			i.getAssociatedUsers().removeIf(us -> us == u);
		}
		
		for(UserRealEstate ure : u.getUserRealEstates()) {
			ure.getRealEstate().removeOwner(ure);	
			this.userRealEstateDao.delete(ure);
		}
		
		for(UserVehicle uv : u.getUserVehicles()) {
			uv.getVehicle().removeOwner(uv);
			this.userVehicleDao.delete(uv);
		}

		for(Family f : this.userDao.getFamilies(u)) {
			this.familyDao.delete(f);
		}	
		
		this.userDao.delete(this.findUser(user));
	}
	
	@Transactional
	@Override
	public void addBankAccount(String user, String IBAN, LocalDate billDate) {
		this.userDao.addBankAccount(this.findUser(user), this.bankAccountDao.findById(IBAN, billDate));
	}
	
	@Override
	public void removeBankAccount(String user, String IBAN, LocalDate billDate) {
		this.userDao.removeBankAccount(this.findUser(user), this.bankAccountDao.findById(IBAN, billDate));
	}
	
	@Transactional
	@Override
	public void addISEE(String user, long isee, String billDate) {
		this.userDao.addAssociatedISEE(this.findUser(user), this.iseeDao.findById(isee));
	}
	
	@Override
	public void removeISEE(String user, long isee) {
		this.userDao.removeAssociatedISEE(this.findUser(user), this.iseeDao.findById(isee));
	}
	
	@Transactional
	@Override
	public void addUserRealEstate(String user, UserRealEstate userRealEstate) {
		this.userDao.addUserRealEstate(this.findUser(user), userRealEstate);
	}
	
	@Override
	public void removeUserRealEstate(String user, UserRealEstate userRealEstate) {
		this.userDao.removeUserRealEstate(this.findUser(user), userRealEstate);
	}
	
	@Transactional
	@Override
	public void addUserVehicle(String user, UserVehicle userVehicle) {
		this.userDao.addUserVehicle(this.findUser(user) ,userVehicle);
	}
	
	@Override
	public void removeUserVehicle(String user, UserVehicle userVehicle) {
		this.userDao.removeUserVehicle(this.findUser(user) ,userVehicle);
	}
	
	@Transactional
	@Override
	public void replaceAccess(long accessId) {
		for(User u : this.findAllUsers() ) {
			if(u.getAccess().equals(this.accessDao.findById(accessId))) {
				u.setAccess(this.accessDao.findByName("UTENTE"));
				this.update(u);
			}	
		}
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setBankAccountDao(BankAccountDao bankAccountDao) {
		this.bankAccountDao = bankAccountDao;
	}
	
	@Autowired
	public void setAccessDao(AccessDao accessDao) {
		this.accessDao = accessDao;
	}
	
	@Autowired
	public void setISEEDao(ISEEDao iseeDao) {
		this.iseeDao = iseeDao;
	}
	
	@Autowired
	public void setIseeDao(ISEEDao iseeDao) {
		this.iseeDao = iseeDao;
	}
	
	@Autowired
	public void setFamilyDao(FamilyDao familyDao) {
		this.familyDao = familyDao;
	}
	
	@Autowired
	public void setUserRealEstateDao(UserRealEstateDao userRealEstateDao) {
		this.userRealEstateDao = userRealEstateDao;
	}
	
	@Autowired
	public void setUserVehicleDao(UserVehicleDao userVehicleDao) {
		this.userVehicleDao = userVehicleDao;
	}
}
