package advprogproj.AgenziaEntrate.services;

import java.util.Set;

import advprogproj.AgenziaEntrate.model.entities.UserISEE;

public interface UserISEEService {
	
	public Set<UserISEE> findAllUserISEEs();
	
	public UserISEE findUserISEE(String user, long isee); 
	
	public UserISEE create(String user, long isee);
	
	public UserISEE update(String user, long isee);
	
	public void delete(String user, long isee);
}
