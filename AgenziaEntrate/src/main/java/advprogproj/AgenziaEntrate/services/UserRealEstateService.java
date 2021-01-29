package advprogproj.AgenziaEntrate.services;

import java.util.Date;

import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;

public interface UserRealEstateService {
	
	public UserRealEstate findUserRealEstate(String user, long realEstate, Date date); 
	
	public UserRealEstate create(String user, long realEstate, Date endOfYear, long price);
	
	public UserRealEstate update(UserRealEstate userRealEstate);
	
	public void delete(UserRealEstate userRealEstate);
}
