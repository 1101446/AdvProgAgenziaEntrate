package advprogproj.AgenziaEntrate.services;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.FamilyDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.UserDaoDefault;
import advprogproj.AgenziaEntrate.model.entities.Family;
import advprogproj.AgenziaEntrate.model.entities.User;

@Service("family")
public class FamilyServiceDefault implements FamilyService{
	
	private FamilyDaoDefault familyDao;
	private UserDaoDefault userDao;
	
	@Transactional
	public Family findById(String user) {
		return this.familyDao.findById(user);
	}
	
	@Transactional
	public Family create(long id, String user, String hierarchy, String houseHolder) {
		return this.familyDao.create(id, this.userDao.findById(user), hierarchy, houseHolder);
	}
	
	@Transactional
	public Family update(String user) {
		return this.update(this.findById(user));
	}
	
	@Transactional
	public Family update(Family family) {
		return this.familyDao.update(family);
	}
	
	@Transactional
	public void delete(Family family) {
		this.familyDao.delete(family);
	}
}
