package advprogproj.AgenziaEntrate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.FamilyDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.UserDaoDefault;
import advprogproj.AgenziaEntrate.model.entities.Family;

@Service("family")
public class FamilyServiceDefault implements FamilyService{
	
	private FamilyDaoDefault familyDao;
	private UserDaoDefault userDao;
	
	@Transactional
	public Family findFamily(long id, String user) {
		return this.familyDao.findById(id, user);
	}
	
	@Transactional
	public Family create(long id, String user, String hierarchy, String houseHolder) {
		return this.familyDao.create(id, this.userDao.findById(user), hierarchy, houseHolder);
	}
	
	@Transactional
	public Family update(long id, String user) {
		return this.findFamily(id, user);
	}
	
	@Transactional
	public void delete(long id, String user) {
		this.findFamily(id, user);
	}
	
	@Autowired
	public void setFamilyDao(FamilyDaoDefault familyDao) {
		this.familyDao = familyDao;
	}
	
	@Autowired
	public void setUserDao(UserDaoDefault userDao) {
		this.userDao = userDao;
	}
	
}
