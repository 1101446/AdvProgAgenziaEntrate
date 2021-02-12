package advprogproj.AgenziaEntrate.model.dao;

import java.util.List;

import org.hibernate.Session;

import advprogproj.AgenziaEntrate.model.entities.Family;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface FamilyDao {
	Session getSession();
	
	public void setSession(Session session);
	
	List<Family> findAll();
	
	Family findById(long id, User user);
	
	public List<Family> findByHouseHolder(String houseHolder);
	
	Family create(long id, User user, String hierarchy, String houseHolder);
	
	Family update(Family family);
	
	void delete(Family family);
}
