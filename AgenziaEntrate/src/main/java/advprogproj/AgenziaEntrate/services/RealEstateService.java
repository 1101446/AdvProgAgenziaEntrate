package advprogproj.AgenziaEntrate.services;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;

public interface RealEstateService {

	public RealEstate findById(long id);
	
	public RealEstate create(String address, String Country, long CAP);
	
	public RealEstate update(RealEstate realEstate);
	
	public void delete(RealEstate realEstate);
}