package advprogproj.AgenziaEntrate.model.dao;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.Access;

public class AccessDaoDefault extends DefaultDao implements AccessDao{
	
	public Access create(long id, String roleName, int priority, String description) {
		Access access = new Access();
		access.setId(id);
		access.setRoleName(roleName);
		access.setPriority(priority);
		access.setDescription(description);
		this.getSession().save(access);
		return access;
	}
	
	public Access update(Access access) {
		return (Access)this.getSession().merge(access);
	}
	
	public void delete(Access access) {
		this.getSession().delete(access);
	}
}
