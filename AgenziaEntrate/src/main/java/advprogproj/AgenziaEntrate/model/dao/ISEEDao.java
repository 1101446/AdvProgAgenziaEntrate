package advprogproj.AgenziaEntrate.model.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import advprogproj.AgenziaEntrate.model.entities.ISEE;
import advprogproj.AgenziaEntrate.model.entities.UserISEE;

public interface ISEEDao {
	
	Session getSession();
	
	public void setSession(Session session);
	
	List<ISEE> findAllWithUser();
	
	List<ISEE> findAll();
	
	ISEE findById(long id);
	
	ISEE create(int yearOfValidity , int valueOfISEE);
	
	ISEE update(ISEE isee);
	
	void delete(ISEE isee);
	
	void addUserAssociated(ISEE isee, UserISEE userISEE);
	
	void removeUserAssociated(ISEE isee, UserISEE userISEE);
	
	Set<UserISEE> getAssociatedUsers(ISEE isee);
}
