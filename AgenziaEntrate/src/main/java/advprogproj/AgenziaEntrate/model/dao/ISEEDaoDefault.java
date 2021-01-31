package advprogproj.AgenziaEntrate.model.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import advprogproj.AgenziaEntrate.model.entities.ISEE;
import advprogproj.AgenziaEntrate.model.entities.User;

@Repository("ISEEDao")
public class ISEEDaoDefault extends DefaultDao implements ISEEDao {

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
	public void addUserAssociated(User user, ISEE isee) {
		user.addAssociatedISEE(isee);
	}

	@Override
	public void removeUserAssociated(User user, ISEE isee) {
		user.removeAssociatedISEE(isee);
	}

	@Override
	public Set<User> getAssociatedUsers(ISEE isee) {
		Query q = this.getSession().createQuery("from User a JOIN FETCH a.associatedISEEs WHERE a.associatedISEEs = :isee", User.class);
		return new HashSet<User>(q.setParameter("associatedISEEs", isee).getResultList());
	}
}
