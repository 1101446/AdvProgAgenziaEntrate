package advprogproj.AgenziaEntrate.services;

import java.util.Set;

import advprogproj.AgenziaEntrate.model.entities.ISEE;
import advprogproj.AgenziaEntrate.model.entities.User;

public interface ISEEService {
	
	public ISEE findISEE(long id);
	
	public ISEE create(int yearOfValidity , int valueOfISEE);
	
	public ISEE update(long id);
	
	public void delete(long id);
	
	public void addUserAssociated(long isee, String user);
	
	public void removeUserAssociated(long isee, String user);

}
