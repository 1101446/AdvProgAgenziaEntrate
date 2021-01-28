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
	
	public User findById(String user);
	
	public User create(String cf, String firstName, String secondName, Date birthDate, String email, String password, boolean handicap, long access);
	
	public User update(String user);
	
	public User update(User user);
	
	public void delete(String user, String bankAccount);
	
	public void addBankAccount(String user, String bankAccount);
	
	public void removeBankAccount(String user, String bankAccount);
}
