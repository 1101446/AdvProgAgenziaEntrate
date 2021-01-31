package advprogproj.AgenziaEntrate.model.dao;

import java.time.LocalDate;
import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface UserRealEstateDao {
	Session getSession();
	
	public void setSession(Session session);
	
	UserRealEstate findById(User user, RealEstate realEstate, LocalDate date);
	
	UserRealEstate create(User user, RealEstate realEstate, LocalDate endOfYear,long price);
	
	UserRealEstate update(UserRealEstate userRealEstate);
	
	void delete(UserRealEstate userRealEstate);
}
