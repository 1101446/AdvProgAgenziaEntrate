package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.Set;

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
	public Set<UserRealEstate> findAllUserRealEstates() {
		return this.userRealEstateDao.findAll();
	}
	
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
	public UserRealEstate update(String user, long realEstate, LocalDate endOfYear, int price) {
		UserRealEstate ure = new UserRealEstate();
		User u = this.userDao.findById(user);
		RealEstate re = this.realEstateDao.findById(realEstate);
		ure.setUser(u);
		ure.setRealEstate(re);
		ure.setEndOfYear(endOfYear);
		ure.setPrice(price);
		this.userRealEstateDao.update(ure);
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
