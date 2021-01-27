package advprogproj.AgenziaEntrate.services;

import java.util.Date;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.entities.User;

@Service("userRealEstate")
public class UserRealEstateServiceDefault implements UserRealEstateService{
	
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
