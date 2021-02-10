package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.Family;
import advprogproj.AgenziaEntrate.model.entities.ISEE;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;

public interface UserService {
	
	public List<User> findAllUsers();
	
	public User findUser(String user);
	
	public User findUserEmail(String email);
	
	public User create(String cf, String firstName, String secondName, LocalDate birthDate, String email, String password, boolean handicap, long access);
	
	public User update(User user);
	
	public void delete(String user);
	
	public void addBankAccount(String user, String bankAccount, LocalDate billDate);
	
	public void removeBankAccount(String user, String bankAccount, LocalDate billDate);
	
	public void addISEE(String user, long isee, String billDate);

	public void removeISEE(String user, long isee);
	
	public void addUserRealEstate(String user, UserRealEstate userRealEstate);

	public void removeUserRealEstate(String user, UserRealEstate userRealEstate);
	
	public void addUserVehicle(String user, UserVehicle userVehicle);

	public void removeUserVehicle(String user, UserVehicle userVehicle);

	void replaceAccess(long accessId);
	
	public Set<Family> getFamilies(User user);

	public Set<BankAccount> getBankAccounts(User user);
	
	public Set<ISEE> getAssociatedISEEs(User user);
	
	public Set<UserVehicle> getUserVehicles(User user);
	
	public Set<UserRealEstate> getUserRealEstates(User user);
}
