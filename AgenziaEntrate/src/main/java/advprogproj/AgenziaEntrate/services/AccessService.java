package advprogproj.AgenziaEntrate.services;

import java.util.List;

import advprogproj.AgenziaEntrate.model.entities.Access;

public interface AccessService {
	
	public List<Access> findAllAccess();
	
	public Access findAccess(long id);
	
	public Access findAccessByName(String name);
	
	public Access create(String roleName, int priority, String description);
	
	public Access update(Access access);
	
	public void delete(long id);
}
