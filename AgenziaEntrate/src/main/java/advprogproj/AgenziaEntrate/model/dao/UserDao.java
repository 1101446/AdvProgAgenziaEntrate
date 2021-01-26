package advprogproj.AgenziaEntrate.model.dao;

import java.util.Date;
import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface UserDao {
	Session getSession();
	
	public void setSession(Session session);
	
	User create(String id, String firstName, String secondName, Date birthDate);
	
	User create(User user);
	
	User update(User user);
	
	void delete(User user);
}
