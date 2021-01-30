package advprogproj.AgenziaEntrate.services;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.Family;

public interface FamilyService {
	
	public Family findFamily(long id, String user);
	
	public Family create(long id, String user, String hierarchy, String houseHolder);
	
	public Family update(long id, String user);
	
	public void delete(long id, String user);
}
