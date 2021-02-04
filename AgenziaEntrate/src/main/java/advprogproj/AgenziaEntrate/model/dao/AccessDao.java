package advprogproj.AgenziaEntrate.model.dao;

import java.util.List;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.Access;

public interface AccessDao {
	Session getSession();
	
	public void setSession(Session session);
	
	List<Access> findAll();
	
	Access findById(long id);
	
	Access create(String roleName, int priority, String description);
	
	Access update(Access access);
	
	void delete(Access access);
}
