package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;

import advprogproj.AgenziaEntrate.model.entities.User;

public interface UserService {
	
	public User findUser(String user);
	
	public User create(String cf, String firstName, String secondName, LocalDate birthDate, String email, String password, boolean handicap, long access);
	
	public User update(String user);
	
	public User update(User user);
	
	public void delete(String user, String bankAccount, LocalDate billDate);
	
	public void addBankAccount(String user, String bankAccount, LocalDate billDate);
	
	public void removeBankAccount(String user, String bankAccount, LocalDate billDate);
}
