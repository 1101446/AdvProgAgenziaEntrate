package advprogproj.AgenziaEntrate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.FamilyDao;
import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.entities.Family;

@Service("family")
public class FamilyServiceDefault implements FamilyService{
	
	private FamilyDao familyDao;
	private UserDao userDao;
	
	@Transactional
	@Override
	public List<Family> findAllFamilies() {
		return this.familyDao.findAll();
	}
	
	@Transactional
	@Override
	public Family findFamily(long id, String user) {
		return this.familyDao.findById(id, this.userDao.findById(user));
	}
	
	@Transactional
	@Override
	public Family create(long id, String user, String hierarchy, String houseHolder) {
		return this.familyDao.create(id, this.userDao.findById(user), hierarchy, houseHolder);
	}
	
	@Transactional
	@Override
	public Family update(Family family) {
		return this.familyDao.update(family);
	}
	
	@Transactional
	@Override
	public void delete(long id, String user) {
		this.familyDao.delete(this.findFamily(id, user));
	}
	
	@Autowired
	public void setFamilyDao(FamilyDao familyDao) {
		this.familyDao = familyDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
