package advprogproj.AgenziaEntrate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.RealEstateDao;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;

@Service("realEstate")
public class RealEstateServiceDefault implements RealEstateService {
	
	private RealEstateDao realEstateDao;
	
	@Transactional
	@Override
	public List<RealEstate> findAllRealEstates() {
		return this.realEstateDao.findAll();
	}
	
	@Transactional
	@Override
	public RealEstate findRealEstate(long id) {
		return this.realEstateDao.findById(id);
	}
	
	@Transactional
	@Override
	public RealEstate create(String address, String Country, int CAP) {
		return this.realEstateDao.create(address, Country, CAP);
	}
	
	@Transactional
	@Override
	public RealEstate update(RealEstate realEstate) {
		return this.realEstateDao.update(realEstate);
	}
	
	@Transactional
	@Override
	public void delete(long realEstate) {
		this.realEstateDao.delete(this.findRealEstate(realEstate));
	}
	
	@Autowired
	public void setRealEstateDao(RealEstateDao realEstateDao) {
		this.realEstateDao = realEstateDao;
	}
	
}
