package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.dao.RealEstateDao;
import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.dao.UserRealEstateDao;

@Service("userRealEstate")
public class UserRealEstateServiceDefault implements UserRealEstateService{
	
	private UserRealEstateDao userRealEstateDao;
	private UserDao userDao;
	private RealEstateDao realEstateDao;
	
	@Transactional
	@Override
	public UserRealEstate findUserRealEstate(String user, long realEstate, LocalDate date) {
		return this.userRealEstateDao.findById(this.userDao.findById(user), this.realEstateDao.findById(realEstate), date);
	}
	
	@Transactional
	@Override
	public UserRealEstate create(String user, long realEstate, LocalDate endOfYear, long price) {
		return this.userRealEstateDao.create(this.userDao.findById(user), this.realEstateDao.findById(realEstate), endOfYear, price);
	}
	
	@Transactional
	@Override
	public UserRealEstate update(UserRealEstate userRealEstate) {
		return this.userRealEstateDao.update(userRealEstate);
	}
	
	@Transactional
	@Override
	public void delete(UserRealEstate userRealEstate) {
		this.userRealEstateDao.delete(userRealEstate);
	}
	
	@Autowired
	public void setUserRealEstateDao(UserRealEstateDao userRealEstateDao) {
		this.userRealEstateDao = userRealEstateDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setRealEstateDao(RealEstateDao realEstateDao) {
		this.realEstateDao = realEstateDao;
	}
}
