package advprogproj.AgenziaEntrate.model.dao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;
import javax.persistence.Query;

import advprogproj.AgenziaEntrate.model.entities.UserISEE;
import advprogproj.AgenziaEntrate.model.entities.ISEE;
import advprogproj.AgenziaEntrate.model.entities.User;

@Repository("userISEEDao")
public class UserISEEDaoDefault extends DefaultDao implements UserISEEDao{
	
	@Override
	public Set<UserISEE> findAll() {
		Query q = this.getSession().createQuery("from UserISEE a join fetch a.user join fetch a.isee", UserISEE.class);
		return new HashSet<UserISEE>(q.getResultList());
	}
	
	@Override
	public UserISEE findById(User user, ISEE isee) {
		Query q = this.getSession().createQuery("from UserISEE a join fetch a.user join fetch a.isee "
				+ "WHERE a.user = :user AND a.isee = :isee", UserISEE.class);
		return (UserISEE) q.setParameter("user", user).
				setParameter("isee", isee).getSingleResult();
	}
	
	@Override
	public UserISEE create(User user, ISEE isee) {
		UserISEE userISEE = new UserISEE();
		userISEE.setUser(user);
		userISEE.setIsee(isee);
		this.getSession().save(userISEE);
		user.addAssociatedISEE(userISEE);
		isee.addAssociatedUser(userISEE);
		return userISEE;
	}
	
	@Override
	public UserISEE update(UserISEE userISEE) {
		return (UserISEE)this.getSession().merge(userISEE);
	}
	
	@Override
	public void delete(UserISEE userISEE) {
		this.getSession().delete(userISEE);
	}
}
