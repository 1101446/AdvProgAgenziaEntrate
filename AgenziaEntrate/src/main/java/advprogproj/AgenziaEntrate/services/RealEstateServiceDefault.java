package advprogproj.AgenziaEntrate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.RealEstateDao;
import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.dao.UserRealEstateDao;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;

@Service("realEstate")
public class RealEstateServiceDefault implements RealEstateService {
	
	private RealEstateDao realEstateDao;
	private UserRealEstateDao userRealEstateDao;
	private UserDao userDao;
	
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
		RealEstate re = this.realEstateDao.findById(realEstate);
		for(UserRealEstate ure : this.realEstateDao.getUserRealEstate(re)) {
			this.userDao.removeUserRealEstate(ure.getUser(), ure);
			this.realEstateDao.removeUserRealEstate(ure.getRealEstate(), ure);
			this.userRealEstateDao.delete(ure);
		}
		this.realEstateDao.delete(this.findRealEstate(realEstate));
	}
	
	@Autowired
	public void setRealEstateDao(RealEstateDao realEstateDao) {
		this.realEstateDao = realEstateDao;
	}
	
	@Autowired
	public void setUserRealEstateDao(UserRealEstateDao userRealEstateDao) {
		this.userRealEstateDao = userRealEstateDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
