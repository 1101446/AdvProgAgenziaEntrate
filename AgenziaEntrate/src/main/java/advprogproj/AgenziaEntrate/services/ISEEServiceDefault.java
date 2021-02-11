package advprogproj.AgenziaEntrate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.ISEEDao;
import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.dao.UserDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.UserISEEDao;
import advprogproj.AgenziaEntrate.model.entities.ISEE;
import advprogproj.AgenziaEntrate.model.entities.UserISEE;

@Service("ISEE")
public class ISEEServiceDefault implements ISEEService{
	
	private ISEEDao iseeDao;
	private UserDao userDao;
	private UserISEEDao userISEEDao;
	
	@Transactional
	@Override
	public List<ISEE> findAllISEEs() {
		return this.iseeDao.findAll();
	}
	
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
	public ISEE update(ISEE isee) {
		return this.iseeDao.update(isee);
	}
	
	@Transactional
	@Override
	public void delete(long id) {
		ISEE i = this.findISEE(id);
		for(UserISEE ui : this.iseeDao.getAssociatedUsers(i)) {
			this.userDao.removeAssociatedISEE(ui.getUser(), ui);
			this.iseeDao.removeUserAssociated(i, ui);
			this.userISEEDao.delete(ui);
		}
		this.iseeDao.delete(i);
	}
	
	@Autowired
	public void setISEEDao(ISEEDao iseeDao) {
		this.iseeDao = iseeDao;
	}
	
	@Autowired
	public void setUserDao(UserDaoDefault userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setUserISEEDao(UserISEEDao userISEEDao) {
		this.userISEEDao = userISEEDao;
	}
}
