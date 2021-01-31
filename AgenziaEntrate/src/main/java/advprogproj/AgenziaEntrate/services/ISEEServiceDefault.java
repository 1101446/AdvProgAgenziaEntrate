package advprogproj.AgenziaEntrate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.ISEEDao;
import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.dao.UserDaoDefault;
import advprogproj.AgenziaEntrate.model.entities.ISEE;

@Service("ISEE")
public class ISEEServiceDefault implements ISEEService{
	
	private ISEEDao iseeDao;
	private UserDao userDao;
	
	@Transactional
	@Override
	public ISEE findISEE(long id) {
		return this.iseeDao.findById(id);
	}
	
	@Transactional
	@Override
	public ISEE create(int yearOfValidity, int valueOfISEE) {
		return this.iseeDao.create(yearOfValidity, valueOfISEE);
	}
	
	@Transactional
	@Override
	public ISEE update(long id) {
		return this.iseeDao.update(this.findISEE(id));
	}
	
	@Transactional
	@Override
	public void delete(long id) {
		this.iseeDao.delete(this.findISEE(id));
	}
	
	@Transactional
	@Override
	public void addUserAssociated(long isee, String user) {
		this.iseeDao.addUserAssociated(this.userDao.findById(user), this.findISEE(isee));
	}
	
	@Transactional
	@Override
	public void removeUserAssociated(long isee, String user) {
		this.iseeDao.removeUserAssociated(this.userDao.findById(user), this.findISEE(isee));
	}
	
	@Autowired
	public void setIseeDao(ISEEDao iseeDao) {
		this.iseeDao = iseeDao;
	}
	
	@Autowired
	public void setUserDao(UserDaoDefault userDao) {
		this.userDao = userDao;
	}
}
