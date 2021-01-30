package advprogproj.AgenziaEntrate.model.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import advprogproj.AgenziaEntrate.model.entities.Family;
import advprogproj.AgenziaEntrate.model.entities.User;

@Repository("familyDao")
public class FamilyDaoDefault extends DefaultDao implements FamilyDao{
	
	public List<Family> findAll(){
		return this.getSession().
				createQuery("from Family a", Family.class).
				getResultList();
	}
	
	public Family findById(long id, String user) {
		Query q = this.getSession().createQuery("from Family a join fetch a.user "
				+ "WHERE a.id = :id AND a.user= :user",Family.class);
		return (Family)q.setParameter("id", id).
				setParameter("user", user).getSingleResult();
	}
	
	public Family create(long id, User user, String hierarchy, String houseHolder) {
		Family family = new Family();
		family.setId(id);
		family.setUser(user);
		family.setHierarchy(hierarchy);
		family.setHouseHolder(houseHolder);
		this.getSession().save(family);
		return family;
	}
	
	public Family update(Family family) {
		return (Family)this.getSession().merge(family);
	}
	
	public void delete(Family family) {
		this.getSession().delete(family);
	}
}
