package advprogproj.AgenziaEntrate.model.dao;

import org.springframework.stereotype.Repository;

import advprogproj.AgenziaEntrate.model.entities.Access;

@Repository("accessDao")
public class AccessDaoDefault extends DefaultDao implements AccessDao{
	
	public Access findById(long id) {
		return this.getSession().find(Access.class, id);
	}
	
	public Access create(String roleName, int priority, String description) {
		Access access = new Access();
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
