package advprogproj.AgenziaEntrate.model.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;

public interface RealEstateDao {
	Session getSession();
	
	public void setSession(Session session);
	
	List<RealEstate> findAll();
	
	RealEstate findById(long id);
	
	RealEstate create(String address, String Country, int CAP);
	
	RealEstate update(RealEstate realEstate);
	
	void delete(RealEstate realEstate);
	
	Set<UserRealEstate> getUserRealEstate(RealEstate realEstate);
	
	public void addUserRealEstate(RealEstate realEstate, UserRealEstate userRealEstate);
	
	public void removeUserRealEstate(RealEstate realEstate, UserRealEstate userRealEstate);
}
