package advprogproj.AgenziaEntrate.services;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;

public interface RealEstateService {

	RealEstate findById(long id);
	
	RealEstate create(String address, String Country, long CAP);
	
	RealEstate update(RealEstate realEstate);
	
	void delete(RealEstate realEstate);
}
