package advprogproj.AgenziaEntrate.model.dao;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;

public interface RealEstateDao {
	Session getSession();
	
	public void setSession(Session session);
	
	RealEstate create(String address, String Country, long CAP);
	
	RealEstate update(RealEstate realEstate);
	
	void delete(RealEstate realEstate);
}
