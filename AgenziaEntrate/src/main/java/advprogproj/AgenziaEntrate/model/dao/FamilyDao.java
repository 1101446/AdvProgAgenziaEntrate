package advprogproj.AgenziaEntrate.model.dao;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.Family;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface FamilyDao {
	Session getSession();
	
	public void setSession(Session session);
	
	Family findById(String User);
	
	Family create(User user, String hierarchy, String houseHolder);
	
	Family update(Family family);
	
	void delete(Family family);
}
