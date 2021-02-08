package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.entities.User;
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
		User u = this.userDao.findById(user);
		RealEstate re = this.realEstateDao.findById(realEstate);
		UserRealEstate newUserRealEstate = this.userRealEstateDao.create(u, re, endOfYear, price);
		return newUserRealEstate;
	}
	
	@Transactional
	@Override
	public UserRealEstate update(UserRealEstate userRealEstate) {
		UserRealEstate ure = this.userRealEstateDao.update(userRealEstate);
		this.userDao.update(userRealEstate.getUser());
		this.realEstateDao.update(userRealEstate.getRealEstate());
		this.userDao.update(ure.getUser());
		this.realEstateDao.update(ure.getRealEstate());
		return ure;
	}
	
	@Transactional
	@Override
	public void delete(String user, long realEstate, LocalDate endOfYear) {
		UserRealEstate ure = this.findUserRealEstate(user, realEstate, endOfYear);
		this.userDao.removeUserRealEstate(ure.getUser(), ure);
		this.realEstateDao.removeUserRealEstate(ure.getRealEstate(), ure);
		this.userRealEstateDao.delete(ure);
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
