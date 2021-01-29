package advprogproj.AgenziaEntrate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.RealEstateDaoDefault;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;

@Service("realEstate")
public class RealEstateServiceDefault implements RealEstateService {
	
	private RealEstateDaoDefault realEstateDao;
	
	@Transactional
	public RealEstate findRealEstate(long id) {
		return this.realEstateDao.findById(id);
	}
	
	@Transactional
	public RealEstate create(String address, String Country, long CAP) {
		return this.realEstateDao.create(address, Country, CAP);
	}
	
	@Transactional
	public RealEstate update(RealEstate realEstate) {
		return this.realEstateDao.update(realEstate);
	}
	
	@Transactional
	public void delete(long realEstate) {
		this.realEstateDao.delete(this.findRealEstate(realEstate));
	}
	
	@Autowired
	public void setRealEstateDao(RealEstateDaoDefault realEstateDao) {
		this.realEstateDao = realEstateDao;
	}
	
}
