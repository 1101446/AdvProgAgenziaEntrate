package advprogproj.AgenziaEntrate.model.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import advprogproj.AgenziaEntrate.model.entities.Family;
import advprogproj.AgenziaEntrate.model.entities.User;

@Repository("familyDao")
public class FamilyDaoDefault extends DefaultDao implements FamilyDao{
	
	@Override
	public List<Family> findAll(){
		return this.getSession().
				createQuery("from Family a", Family.class).
				getResultList();
	}
	
	@Override
	public Family findById(long id, User user) {
		Query q = this.getSession().createQuery("from Family a WHERE a.id = :id AND a.user= :user",Family.class);
		return (Family)q.setParameter("id", id).
				setParameter("user", user).getSingleResult();
	}
	
	@Override
	public Family create(long id, User user, String hierarchy, String houseHolder) {
		Family family = new Family();
		family.setId(id);
		family.setUser(user);
		family.setHierarchy(hierarchy);
		family.setHouseHolder(houseHolder);
		this.getSession().save(family);
		return family;
	}
	
	@Override
	public Family update(Family family) {
		return (Family)this.getSession().merge(family);
	}
	
	@Override
	public void delete(Family family) {
		this.getSession().delete(family);
	}
}
