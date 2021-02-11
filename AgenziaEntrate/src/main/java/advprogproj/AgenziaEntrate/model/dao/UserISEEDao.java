package advprogproj.AgenziaEntrate.model.dao;

import java.util.Set;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.UserISEE;
import advprogproj.AgenziaEntrate.model.entities.ISEE;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface UserISEEDao {
	Session getSession();
	
	public void setSession(Session session);
	
	Set<UserISEE> findAll();
	
	UserISEE findById(User user, ISEE isee);
	
	UserISEE create(User user, ISEE isee);
	
	UserISEE update(UserISEE userISEE);
	
	void delete(UserISEE userISEE);
}
