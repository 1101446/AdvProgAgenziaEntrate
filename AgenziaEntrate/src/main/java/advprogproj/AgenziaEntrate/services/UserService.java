package advprogproj.AgenziaEntrate.services;

import java.util.Date;

import advprogproj.AgenziaEntrate.model.entities.User;

public interface UserService {
	
	public User findUser(String user);
	
	public User create(String cf, String firstName, String secondName, Date birthDate, String email, String password, boolean handicap, long access);
	
	public User update(String user);
	
	public User update(User user);
	
	public void delete(String user, String bankAccount);
	
	public void addBankAccount(String user, String bankAccount);
	
	public void removeBankAccount(String user, String bankAccount);
}
