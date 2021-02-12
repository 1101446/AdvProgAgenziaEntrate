package advprogproj.AgenziaEntrate.services;

import java.util.List;

import advprogproj.AgenziaEntrate.model.entities.Family;

public interface FamilyService {
	
	public List<Family> findAllFamilies();
	
	public Family findFamily(long id, String user);
	
	public Family create(long id, String user, String hierarchy, String houseHolder);
	
	public Family update(Family family);
	
	public void delete(long id, String user);
}
