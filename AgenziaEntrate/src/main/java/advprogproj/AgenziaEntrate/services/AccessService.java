package advprogproj.AgenziaEntrate.services;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.Access;

public interface AccessService {
	Session getSession();
	
	public void setSession(Session session);
	
	Access findById(long id);
	
	Access create(String roleName, int priority, String description);
	
	Access update(Access access);
	
	void delete(Access access);
}
