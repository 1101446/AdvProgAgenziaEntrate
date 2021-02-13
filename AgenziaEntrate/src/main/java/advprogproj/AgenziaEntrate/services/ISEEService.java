package advprogproj.AgenziaEntrate.services;

import java.util.List;

import advprogproj.AgenziaEntrate.model.entities.ISEE;

public interface ISEEService {
	
	public List<ISEE> findAllISEEs();
	
	public ISEE findISEE(long id);
	
	public ISEE create(int yearOfValidity , int valueOfISEE);
	
	public ISEE update(ISEE isee);
	
	public void delete(long id);
}
