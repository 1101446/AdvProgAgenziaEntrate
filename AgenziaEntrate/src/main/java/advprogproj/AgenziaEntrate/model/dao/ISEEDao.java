package advprogproj.AgenziaEntrate.model.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import advprogproj.AgenziaEntrate.model.entities.ISEE;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface ISEEDao {
	
	Session getSession();
	
	public void setSession(Session session);
	
	List<ISEE> findAll();
	
	ISEE findById(long id);
	
	ISEE create(long id, int yearOfValidity , int valueOfISEE);
	
	ISEE update(ISEE isee);
	
	void delete(ISEE isee);
	
	void addUserAssociated(User user, ISEE isee);
	
	void removeUserAssociated(User user, ISEE isee);
	
	Set<User> getAssociatedUsers(ISEE isee);
}
