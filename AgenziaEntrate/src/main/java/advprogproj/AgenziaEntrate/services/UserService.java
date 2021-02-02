package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.List;

import advprogproj.AgenziaEntrate.model.entities.User;

public interface UserService {
	
	public List<User> findAllUsers();
	
	public User findUser(String user);
	
	public User create(String cf, String firstName, String secondName, LocalDate birthDate, String email, String password, boolean handicap, long access);
	
	public User update(String user);
	
	public User update(User user);
	
	public void delete(String user, String bankAccount, String billDate);
	
	public void addBankAccount(String user, String bankAccount, String billDate);
	
	public void removeBankAccount(String user, String bankAccount, String billDate);
}
