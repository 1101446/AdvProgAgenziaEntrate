package advprogproj.AgenziaEntrate.model.dao;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.Access;

public interface AccessDao {
	Session getSession();
	
	public void setSession(Session session);
	
	Access create(String roleName, int priority, String description);
	
	Access update(Access access);
	
	void delete(Access access);
}
