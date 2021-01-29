package advprogproj.AgenziaEntrate.model.dao;

import java.util.Date;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;

import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.entities.User;

@Repository("userRealEstateDao")
public class UserRealEstateDaoDefault extends DefaultDao implements UserRealEstateDao{
	
	public UserRealEstate findById(User user, RealEstate realEstate, Date date) {
		Query q = this.getSession().createQuery("from UserRealEstate a join fetch a.user join fetch a.realEstate"
				+ "WHERE a.user = :user AND a.realEstate = :realEstate AND a.endOfYear = :date", UserRealEstate.class);
		return (UserRealEstate) q.setParameter("user", user).
				setParameter("realEstate", realEstate).
				setParameter("date", date).getResultList();
	}
	
	public UserRealEstate create(User user, RealEstate realEstate, Date endOfYear, long price) {
		UserRealEstate userRealEstate = new UserRealEstate();
		userRealEstate.setUser(user);
		userRealEstate.setRealEstate(realEstate);
		userRealEstate.setEndOfYear(endOfYear);
		userRealEstate.setPrice(price);
		this.getSession().save(userRealEstate);
		return userRealEstate;
	}
	
	public UserRealEstate update(UserRealEstate userRealEstate) {
		return (UserRealEstate)this.getSession().merge(userRealEstate);
	}
	
	public void delete(UserRealEstate userRealEstate) {
		this.getSession().delete(userRealEstate);
	}
}
