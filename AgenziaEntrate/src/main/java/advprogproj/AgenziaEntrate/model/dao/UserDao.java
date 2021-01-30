package advprogproj.AgenziaEntrate.model.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;

import advprogproj.AgenziaEntrate.model.entities.Access;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;

public interface UserDao {
	Session getSession();
	
	public void setSession(Session session);
	
	List<User> findAll();
	
	User findById(String id);
	
	User findByEmail(String email);
	
	User create(String cf, String firstName, String secondName, LocalDate birthDate, String email, String password, boolean handicap, Access access);
	
	User update(User user);
	
	void delete(User user);
	
	Set<BankAccount> getBankAccounts(User user);
	
	Set<UserVehicle> getUserVehicles(User user);
	
	Set<UserRealEstate> getUserRealEstate(User user);
	
	void addBankAccount(User user, BankAccount bankAccount);
	
	void removeBankAccount(User user, BankAccount bankAccount);
	
	public void addUserVehicle(User user, UserVehicle userVehicle);
	
	public void removeUserVehicle(User user, UserVehicle userVehicle);
	
	public void addUserRealEstate(User user, UserRealEstate userRealEstate);
	
	public void removeUserRealEstate(User user, UserRealEstate userRealEstate) ;
	
	public String encryptPassword(String password);
}
