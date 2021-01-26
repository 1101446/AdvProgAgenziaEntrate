package advprogproj.AgenziaEntrate.model.dao;

import java.util.Date;
import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface UserDao {
	Session getSession();
	
	public void setSession(Session session);
	
	User findById(String id);
	
	User create(String cf, String firstName, String secondName, Date birthDate, String email, String password, boolean handicap);
	
	User update(User user);
	
	void delete(User user);
}
