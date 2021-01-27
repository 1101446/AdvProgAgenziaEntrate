package advprogproj.AgenziaEntrate.model.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import advprogproj.AgenziaEntrate.model.entities.Family;
import advprogproj.AgenziaEntrate.model.entities.User;

@Repository("familyDao")
public class FamilyDaoDefault extends DefaultDao implements FamilyDao{
	
	public Family findById(String User) {
		return this.getSession().find(Family.class, User);
	}
	
	public Family create(User user, String hierarchy, String houseHolder) {
		Family family = new Family();
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
