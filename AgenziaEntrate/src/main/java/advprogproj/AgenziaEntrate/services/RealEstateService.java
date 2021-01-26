package advprogproj.AgenziaEntrate.services;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;

public interface RealEstateService {
	Session getSession();
	
	public void setSession(Session session);
	
	RealEstate findById(long id);
	
	RealEstate create(String address, String Country, long CAP);
	
	RealEstate update(RealEstate realEstate);
	
	void delete(RealEstate realEstate);
}
