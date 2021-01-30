package advprogproj.AgenziaEntrate.services;

import advprogproj.AgenziaEntrate.model.entities.RealEstate;

public interface RealEstateService {

	public RealEstate findRealEstate(long id);
	
	public RealEstate create(String address, String Country, int CAP);
	
	public RealEstate update(RealEstate realEstate);
	
	public void delete(long realEstate);
	
}
