package advprogproj.AgenziaEntrate.model.dao;

import java.util.Date;
import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface UserRealEstateDao {
	Session getSession();
	
	public void setSession(Session session);
	
	UserRealEstate create(User user, RealEstate realEstate, Date endOfYear,long price);
	
	UserRealEstate update(UserRealEstate userRealEstate);
	
	void delete(UserRealEstate userRealEstate);
	
}
