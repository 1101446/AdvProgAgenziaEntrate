package advprogproj.AgenziaEntrate.model.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.Access;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.dao.DefaultDao;

@Repository("userDao")
public class UserDaoDefault extends DefaultDao implements UserDao{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User findById(String id) {
		return getSession().find(User.class, id);
	}
	
	public User create(String cf, String firstName, String secondName, Date birthDate, String email, String password, boolean handicap, Access access) {
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
	
	public User update(User user) {
		return (User)this.getSession().merge(user);
	}
	
	public void delete(User user) {
		this.getSession().delete(user);
	}
	
	public Set<BankAccount> getBankAccounts(User user) {
		Query q = this.getSession().createQuery("from BankAccount a JOIN FETCH a.owner WHERE a.owner = :user", BankAccount.class);
		return new HashSet<BankAccount>(q.setParameter("user", user).getResultList());
	}
	
	public Set<UserVehicle> getUserVehicles(User user) {
		Query q = this.getSession().createQuery("from UserVehicle a JOIN FETCH a.user WHERE a.user = :user", UserVehicle.class);
		return new HashSet<UserVehicle>(q.setParameter("user", user).getResultList());
	}
	
	public Set<UserRealEstate> getUserRealEstate(User user) {
		Query q = this.getSession().createQuery("from UserRealEstate a JOIN FETCH a.user WHERE a.user = :user", UserRealEstate.class);
		return new HashSet<UserRealEstate>(q.setParameter("user", user).getResultList());
	}
	
	public String encryptPassword(String password) {
		return this.passwordEncoder.encode(password);
	}
}
