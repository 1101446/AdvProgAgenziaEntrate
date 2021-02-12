package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.Family;
import advprogproj.AgenziaEntrate.model.entities.ISEE;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.UserBankAccount;
import advprogproj.AgenziaEntrate.model.entities.UserISEE;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;

public interface UserService {
	
	public List<User> findAllUsers();
	
	public User findUser(String user);
	
	public User findUserEmail(String email);
	
	public User create(String cf, String firstName, String secondName, LocalDate birthDate, String email, String password, boolean handicap, long access);
	
	public User update(User user);
	
	public void delete(String user);
	
	public void addBankAccount(String user, UserBankAccount userBankAccount);
	
	public void removeBankAccount(String user, UserBankAccount userBankAccount);
	
	public void addISEE(String user, UserISEE userISEE);

	public void removeISEE(String user, UserISEE userISEE);
	
	public void addUserRealEstate(String user, UserRealEstate userRealEstate);

	public void removeUserRealEstate(String user, UserRealEstate userRealEstate);
	
	public void addUserVehicle(String user, UserVehicle userVehicle);

	public void removeUserVehicle(String user, UserVehicle userVehicle);

	void replaceAccess(long accessId);
	
	public List<Family> getFamilies(User user);
	
	public Set<UserBankAccount> getUserBankAccounts(User user);
	
	public Set<UserISEE> getAssociatedISEEs(User user);
	
	public Set<UserVehicle> getUserVehicles(User user);
	
	public Set<UserRealEstate> getUserRealEstates(User user);
}
