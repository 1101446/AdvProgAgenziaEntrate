package advprogproj.AgenziaEntrate.model.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import advprogproj.AgenziaEntrate.model.entities.ISEE;
import advprogproj.AgenziaEntrate.model.entities.UserISEE;

@Repository("ISEEDao")
public class ISEEDaoDefault extends DefaultDao implements ISEEDao {

	@Override
	public List<ISEE> findAllWithUser() {
		return this.getSession().createQuery("from ISEE a join fetch a.associatedUsers", ISEE.class).getResultList();
	}
	
	@Override
	public List<ISEE> findAll() {
		return this.getSession().createQuery("from ISEE a", ISEE.class).getResultList();
	}
	
	@Override
	public ISEE findById(long id) {
		return this.getSession().find(ISEE.class, id);
	}

	@Override
	public ISEE create(int yearOfValidity, int valueOfISEE) {
		ISEE isee = new ISEE();
		isee.setYearOfValidity(yearOfValidity);
		isee.setValueOfISEE(valueOfISEE);
		this.getSession().save(isee);
		return isee;
	}

	@Override
	public ISEE update(ISEE isee) {
		return (ISEE)this.getSession().merge(isee);
	}

	@Override
	public void delete(ISEE isee) {
		this.getSession().remove(isee);
	}

	@Override
	public void addUserAssociated(ISEE isee, UserISEE userISEE) {
		isee.addAssociatedUser(userISEE);
	}

	@Override
	public void removeUserAssociated(ISEE isee, UserISEE userISEE) {
		isee.removeAssociatedUser(userISEE);
	}

	@Override
	public Set<UserISEE> getAssociatedUsers(ISEE isee) {
		Query q = this.getSession().createQuery("from UserISEE a join fetch a.ISEE WHERE a.ISEE = :isee", UserISEE.class);
		return new HashSet<UserISEE>(q.setParameter("isee", isee).getResultList());
	}
}
