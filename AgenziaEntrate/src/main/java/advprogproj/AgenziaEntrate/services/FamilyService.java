package advprogproj.AgenziaEntrate.services;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.Family;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface FamilyService {
	
	Family findById(String User);
	
	Family create(User user, String hierarchy, String houseHolder);
	
	Family update(Family family);
	
	void delete(Family family);
}
