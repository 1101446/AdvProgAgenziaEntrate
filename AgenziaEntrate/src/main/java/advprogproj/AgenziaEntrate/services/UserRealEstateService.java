package advprogproj.AgenziaEntrate.services;

import java.util.Date;
import java.util.Set;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface UserRealEstateService {
	
	public UserRealEstate create(User user, RealEstate realEstate, Date endOfYear,long price);
	
	public UserRealEstate update(UserRealEstate userRealEstate);
	
	public void delete(UserRealEstate userRealEstate);
	
	public Set<UserRealEstate> getUserRealEstate(RealEstate realEstate);
}
