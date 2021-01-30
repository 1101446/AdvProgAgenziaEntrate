package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;

import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;

public interface UserRealEstateService {
	
	public UserRealEstate findUserRealEstate(String user, long realEstate, LocalDate date); 
	
	public UserRealEstate create(String user, long realEstate, LocalDate endOfYear, long price);
	
	public UserRealEstate update(UserRealEstate userRealEstate);
	
	public void delete(UserRealEstate userRealEstate);
}
