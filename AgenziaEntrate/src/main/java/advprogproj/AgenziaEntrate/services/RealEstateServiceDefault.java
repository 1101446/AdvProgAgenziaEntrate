package advprogproj.AgenziaEntrate.services;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import advprogproj.AgenziaEntrate.model.dao.RealEstateDaoDefault;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;

@Service("realEstate")
public class RealEstateServiceDefault implements RealEstateService {
	
	private RealEstateDaoDefault realEstateDao;
	
	public RealEstate findById(long id) {
		return this.realEstateDao.findById(id);
	}
	
	public RealEstate create(String address, String Country, long CAP) {
		return this.realEstateDao.create(address, Country, CAP);
	}
	
	public RealEstate update(RealEstate realEstate) {
		return this.realEstateDao.update(realEstate);
	}
	
	public void delete(RealEstate realEstate) {
		this.realEstateDao.delete(realEstate);
	}
	
}
