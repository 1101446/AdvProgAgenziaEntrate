package advprogproj.AgenziaEntrate.model.dao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;
import javax.persistence.Query;

import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.entities.User;

@Repository("userRealEstateDao")
public class UserRealEstateDaoDefault extends DefaultDao implements UserRealEstateDao{
	
	@Override
	public Set<UserRealEstate> findAll() {
		Query q = this.getSession().createQuery("from UserRealEstate a join fetch a.user join fetch a.realEstate", UserRealEstate.class);
		return new HashSet<UserRealEstate>(q.getResultList());
	}
	
	@Override
	public UserRealEstate findById(User user, RealEstate realEstate, LocalDate date) {
		Query q = this.getSession().createQuery("from UserRealEstate a join fetch a.user join fetch a.realEstate "
				+ "WHERE a.user = :user AND a.realEstate = :realEstate AND a.endOfYear = :date", UserRealEstate.class);
		return (UserRealEstate) q.setParameter("user", user).
				setParameter("realEstate", realEstate).
				setParameter("date", date).getSingleResult();
	}
	
	@Override
	public UserRealEstate create(User user, RealEstate realEstate, LocalDate endOfYear, long price) {
		UserRealEstate userRealEstate = new UserRealEstate();
		userRealEstate.setUser(user);
		userRealEstate.setRealEstate(realEstate);
		userRealEstate.setEndOfYear(endOfYear);
		userRealEstate.setPrice(price);
		this.getSession().save(userRealEstate);
		user.addUserRealEstate(userRealEstate);
		realEstate.addOwner(userRealEstate);
		return userRealEstate;
	}
	
	@Override
	public UserRealEstate update(UserRealEstate userRealEstate) {
		return (UserRealEstate)this.getSession().merge(userRealEstate);
	}
	
	@Override
	public void delete(UserRealEstate userRealEstate) {
		this.getSession().delete(userRealEstate);
	}
}
