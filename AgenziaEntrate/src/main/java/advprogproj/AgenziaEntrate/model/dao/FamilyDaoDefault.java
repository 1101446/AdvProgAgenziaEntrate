package advprogproj.AgenziaEntrate.model.dao;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.Family;
import advprogproj.AgenziaEntrate.model.entities.User;

public class FamilyDaoDefault extends DefaultDao implements FamilyDao{
	
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
