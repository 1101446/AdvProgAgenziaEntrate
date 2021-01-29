package advprogproj.AgenziaEntrate.services;

import java.util.Date;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.dao.RealEstateDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.UserDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.UserRealEstateDaoDefault;

@Service("userRealEstate")
public class UserRealEstateServiceDefault implements UserRealEstateService{
	
	private UserRealEstateDaoDefault userRealEstateDao;
	private UserDaoDefault userDao;
	private RealEstateDaoDefault realEstateDao;
	
	@Transactional
	public UserRealEstate findUserRealEstate(String user, long realEstate, Date date) {
		return this.userRealEstateDao.findById(this.userDao.findById(user), this.realEstateDao.findById(realEstate), date);
	}
	
	@Transactional
	public UserRealEstate create(String user, long realEstate, Date endOfYear, long price) {
		UserRealEstate ure = this.userRealEstateDao.create(this.userDao.findById(user), this.realEstateDao.findById(realEstate), endOfYear, price);
		this.userDao.addUserRealEstate(this.userDao.findById(user), ure);
		this.realEstateDao.addUserRealEstate(this.realEstateDao.findById(realEstate), ure);
		return ure;
	}
	
	@Transactional
	public UserRealEstate update(UserRealEstate userRealEstate) {
		return this.userRealEstateDao.update(userRealEstate);
	}
	
	@Transactional
	public void delete(UserRealEstate userRealEstate) {
		this.userRealEstateDao.delete(userRealEstate);
		this.userDao.removeUserRealEstate(userRealEstate.getUser(), userRealEstate);
		this.realEstateDao.removeUserRealEstate(userRealEstate.getRealEstate(), userRealEstate);
	}

}
