package advprogproj.AgenziaEntrate.services;

import java.util.Date;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface UserRealEstateService {
	
	public UserRealEstate findById(String user, long realEstate, Date date); 
	
	public User findUser(String user);
	
	public RealEstate findRealEstate(long realEstate);
	
	public UserRealEstate create(String user, long realEstate, Date endOfYear, long price);
	
	public UserRealEstate update(UserRealEstate userRealEstate);
	
	public void delete(UserRealEstate userRealEstate);
	
	public Set<UserRealEstate> getUserRealEstate(RealEstate realEstate);
}
