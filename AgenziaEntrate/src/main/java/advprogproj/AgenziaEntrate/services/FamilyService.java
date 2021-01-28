package advprogproj.AgenziaEntrate.services;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.Family;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface FamilyService {
	
	public Family findById(String user);
	
	public Family create(long id, String user, String hierarchy, String houseHolder);
	
	public Family update(Family family);
	
	public void delete(Family family);
}
