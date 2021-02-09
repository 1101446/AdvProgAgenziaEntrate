package advprogproj.AgenziaEntrate.model.dao;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Repository;
import javax.persistence.Query;

import advprogproj.AgenziaEntrate.model.entities.Access;

@Repository("accessDao")
public class AccessDaoDefault extends DefaultDao implements AccessDao{
	
	@Override
	public List<Access> findAll() {
		return this.getSession().createQuery("from Access a", Access.class).getResultList();
	}
	
	@Override
	public Access findById(long id) {
		return this.getSession().find(Access.class, id);
	}
	
	@Override
	public Access findByName(String name) {
		Query q = this.getSession().createQuery("from Access a WHERE a.roleName = :name", Access.class);
		return (Access)q.setParameter("name", name).getSingleResult();
	}
	
	@Override
	public Access create(String roleName, int priority, String description) {
		Access access = new Access();
		access.setRoleName(roleName);
		access.setPriority(priority);
		access.setDescription(description);
		this.getSession().save(access);
		return access;
	}
	
	@Override
	public Access update(Access access) {
		return (Access)this.getSession().merge(access);
	}
	
	@Override
	public void delete(Access access) {
		this.getSession().delete(access);
	}
}
