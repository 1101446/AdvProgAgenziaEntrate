package advprogproj.AgenziaEntrate.services;

import advprogproj.AgenziaEntrate.model.entities.Access;

public interface AccessService {
	
	public Access findAccess(long id);
	
	public Access create(String roleName, int priority, String description);
	
	public Access update(Access access);
	
	public void delete(Access access);
}
