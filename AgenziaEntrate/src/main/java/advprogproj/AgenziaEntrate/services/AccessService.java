package advprogproj.AgenziaEntrate.services;

import advprogproj.AgenziaEntrate.model.entities.Access;

public interface AccessService {
	
	Access findById(long id);
	
	Access create(String roleName, int priority, String description);
	
	Access update(Access access);
	
	void delete(Access access);
}
