package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.Set;

import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;

public interface UserRealEstateService {
	
	public Set<UserRealEstate> findAllUserRealEstates();
	
	public UserRealEstate findUserRealEstate(String user, long realEstate, LocalDate date); 
	
	public UserRealEstate create(String user, long realEstate, LocalDate endOfYear, long price);
	
	public UserRealEstate update(String user, long realEstate, LocalDate endOfYear, int price);
	
	public void delete(String user, long realEstate, LocalDate endOfYear);
}
