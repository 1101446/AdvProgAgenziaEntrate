package advprogproj.AgenziaEntrate.model.dao;

import java.util.List;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;

public interface RealEstateDao {
	Session getSession();
	
	public void setSession(Session session);
	
	List<RealEstate> findAll();
	
	RealEstate findById(long id);
	
	RealEstate create(String address, String Country, long CAP);
	
	RealEstate update(RealEstate realEstate);
	
	void delete(RealEstate realEstate);
}
