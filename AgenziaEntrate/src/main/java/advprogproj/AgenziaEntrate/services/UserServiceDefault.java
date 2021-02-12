package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.UserBankAccount;
import advprogproj.AgenziaEntrate.model.entities.UserISEE;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;
import advprogproj.AgenziaEntrate.model.entities.Family;
import advprogproj.AgenziaEntrate.model.dao.AccessDao;
import advprogproj.AgenziaEntrate.model.dao.UserBankAccountDao;
import advprogproj.AgenziaEntrate.model.dao.FamilyDao;
import advprogproj.AgenziaEntrate.model.dao.UserISEEDao;
import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.dao.UserRealEstateDao;
import advprogproj.AgenziaEntrate.model.dao.UserVehicleDao;

@Service("userService")
public class UserServiceDefault implements UserService, UserDetailsService{
	
	@Autowired
	private UserDao userDao;
	private AccessDao accessDao;
	private FamilyDao familyDao;
	private UserBankAccountDao userBankAccountDao;
	private UserRealEstateDao userRealEstateDao;
	private UserVehicleDao userVehicleDao;
	private UserISEEDao userISEEDao;
	
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
		
		for(UserBankAccount ubk : u.getBankAccounts()) {
			ubk.getBankAccount().removeOwner(ubk);
			this.userBankAccountDao.delete(ubk);
		}
		
		for(UserISEE ui : u.getAssociatedISEEs()) {
			ui.getIsee().removeAssociatedUser(ui);
			this.userISEEDao.delete(ui);
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
	public void addBankAccount(String user, UserBankAccount userBankAccount) {
		this.userDao.addBankAccount(this.findUser(user), userBankAccount);
	}
	
	@Override
	public void removeBankAccount(String user, UserBankAccount userBankAccount) {
		this.userDao.removeBankAccount(this.findUser(user), userBankAccount);
	}
	
	@Transactional
	@Override
	public void addISEE(String user, UserISEE userISEE) {
		this.userDao.addAssociatedISEE(this.findUser(user), userISEE);
	}
	
	@Override
	public void removeISEE(String user, UserISEE userISEE) {
		this.userDao.removeAssociatedISEE(this.findUser(user), userISEE);
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
	
	@Transactional
	@Override
	public List<Family> getFamilies(User user) {
		List<Family> families = new ArrayList<Family>();
		for(Family a : this.userDao.getFamilies(user)) {
			families.addAll(this.familyDao.findByHouseHolder(a.getHouseHolder()));
		}
		return families;
	}
	
	@Transactional
	@Override
	public Set<UserBankAccount> getUserBankAccounts(User user) {
		return this.userDao.getBankAccounts(user);
	}
	
	@Transactional
	@Override
	public Set<UserISEE> getAssociatedISEEs(User user) {
		return this.userDao.getAssociatedISEEs(user);
	}
	
	@Transactional
	@Override
	public Set<UserVehicle> getUserVehicles(User user) {
		return this.userDao.getUserVehicles(user);
	}
	
	@Transactional
	@Override
	public Set<UserRealEstate> getUserRealEstates(User user) {
		return this.userDao.getUserRealEstates(user);
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setUserBankAccountDao(UserBankAccountDao userBankAccountDao) {
		this.userBankAccountDao = userBankAccountDao;
	}
	
	@Autowired
	public void setAccessDao(AccessDao accessDao) {
		this.accessDao = accessDao;
	}
	
	@Autowired
	public void setISEEDao(UserISEEDao userISEEDao) {
		this.userISEEDao = userISEEDao;
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
