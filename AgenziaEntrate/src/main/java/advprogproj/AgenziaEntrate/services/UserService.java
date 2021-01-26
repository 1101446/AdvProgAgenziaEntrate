package advprogproj.AgenziaEntrate.services;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;

import advprogproj.AgenziaEntrate.model.entities.Access;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;

public interface UserService {
	Session getSession();
	
	public void setSession(Session session);
	
	User findById(String id);
	
	User create(String cf, String firstName, String secondName, Date birthDate, String email, String password, boolean handicap, Access access);
	
	User update(User user);
	
	void delete(User user);
	
	Set<BankAccount> getBankAccounts(User user);
	
	Set<UserVehicle> getUserVehicles(User user);
	
	Set<UserRealEstate> getUserRealEstate(User user);
	
	public String encryptPassword(String password);
}
