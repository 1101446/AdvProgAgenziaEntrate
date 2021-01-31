package advprogproj.AgenziaEntrate.model.dao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.ISEE;
import advprogproj.AgenziaEntrate.model.entities.Access;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.dao.DefaultDao;

@Repository("userDao")
public class UserDaoDefault extends DefaultDao implements UserDao{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> findAll(){
		return this.getSession().createQuery("from User a", User.class).getResultList();
	}
	
	@Override
	public User findById(String id) {
		return getSession().find(User.class, id);
	}
	
	@Override
	public User findByEmail(String email) {
		Query q = this.getSession().createQuery("from User a WHERE a.email = :email", User.class);
		return (User)q.setParameter("email", email).getSingleResult();
	}
	
	@Override
	public User create(String cf, String firstName, String secondName, LocalDate birthDate, String email, String password, boolean handicap, Access access) {
		User user = new User();
		user.setCf(cf);
		user.setFirstName(firstName);
		user.setSecondName(secondName);
		user.setBirthD(birthDate);
		user.setEmail(email);
		user.setPassword(password);
		user.setHandicap(handicap);
		user.setAccess(access);
		this.getSession().save(user);
		return user;
	}
	
	@Override
	public User update(User user) {
		return (User)this.getSession().merge(user);
	}
	
	@Override
	public void delete(User user) {
		this.getSession().delete(user);
	}
	
	@Override
	public Set<BankAccount> getBankAccounts(User user) {
		Query q = this.getSession().createQuery("from BankAccount a join fetch a.owners WHERE a.owners = :user", BankAccount.class);
		return new HashSet<BankAccount>(q.setParameter("user", user).getResultList());
	}
	
	@Override
	public Set<ISEE> getAssociatedISEEs(User user) {
		Query q = this.getSession().createQuery("from ISEE a join fetch a.associatedUsers WHERE a.associatedUsers = :user", ISEE.class);
		return new HashSet<ISEE>(q.setParameter("user", user).getResultList());
	}
	
	@Override
	public Set<UserVehicle> getUserVehicles(User user) {
		Query q = this.getSession().createQuery("from UserVehicle a join fetch a.user WHERE a.user = :user", UserVehicle.class);
		return new HashSet<UserVehicle>(q.setParameter("user", user).getResultList());
	}
	
	@Override
	public Set<UserRealEstate> getUserRealEstates(User user) {
		Query q = this.getSession().createQuery("from UserRealEstate a join fetch a.user WHERE a.user = :user", UserRealEstate.class);
		return new HashSet<UserRealEstate>(q.setParameter("user", user).getResultList());
	}
	
	@Override
	public void addBankAccount(User user, BankAccount bankAccount) {
		user.addBankAccount(bankAccount);
	}
	
	@Override
	public void removeBankAccount(User user, BankAccount bankAccount) {
		user.removeBankAccount(bankAccount);
	}
	
	@Override
	public void addAssociatedISEE(User user, ISEE isee) {
		user.addAssociatedISEE(isee);
	}
	
	@Override
	public void removeAssociatedISEE(User user, ISEE isee) {
		user.removeAssociatedISEE(isee);
	}

	@Override
	public void addUserVehicle(User user, UserVehicle userVehicle) {
		user.addUserVehicle(userVehicle);
	}
	
	@Override
	public void removeUserVehicle(User user, UserVehicle userVehicle) {
		user.removeUserVehicle(userVehicle);
	}
	
	@Override
	public void addUserRealEstate(User user, UserRealEstate userRealEstate) {
		user.addUserRealEstate(userRealEstate);
	}
	
	@Override
	public void removeUserRealEstate(User user, UserRealEstate userRealEstate) {
		user.removeUserRealEstate(userRealEstate);
	}
	
	public String encryptPassword(String password) {
		return this.passwordEncoder.encode(password);
	}
}
