package advprogproj.AgenziaEntrate.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.entities.ISEE;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.UserISEE;
import advprogproj.AgenziaEntrate.model.dao.ISEEDao;
import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.dao.UserISEEDao;

@Service("userISEE")
public class UserISEEServiceDefault implements UserISEEService{
	
	private UserISEEDao userISEEDao;
	private UserDao userDao;
	private ISEEDao iseeDao;
	
	@Transactional
	@Override
	public Set<UserISEE> findAllUserISEEs() {
		return this.userISEEDao.findAll();
	}
	
	@Transactional
	@Override
	public UserISEE findUserISEE(String user, long isee) {
		return this.userISEEDao.findById(this.userDao.findById(user), this.iseeDao.findById(isee));
	}
	
	@Transactional
	@Override
	public UserISEE create(String user, long isee) {
		User u = this.userDao.findById(user);
		ISEE i = this.iseeDao.findById(isee);
		UserISEE newUserISEE = this.userISEEDao.create(u, i);
		return newUserISEE;
	}
	
	@Transactional
	@Override
	public UserISEE update(String user, long isee) {
		UserISEE ui = new UserISEE();
		User u = this.userDao.findById(user);
		ISEE i = this.iseeDao.findById(isee);
		ui.setUser(u);
		ui.setIsee(i);
		this.userISEEDao.update(ui);
		this.userDao.update(ui.getUser());
		this.iseeDao.update(ui.getIsee());
		return ui;
	}
	
	@Transactional
	@Override
	public void delete(String user, long isee) {
		UserISEE ui = this.findUserISEE(user, isee);
		this.userDao.removeAssociatedISEE(ui.getUser(), ui);
		this.iseeDao.removeUserAssociated(ui.getIsee(), ui);
		this.userISEEDao.delete(ui);
	}
	
	@Autowired
	public void setUserISEEDao(UserISEEDao userISEEDao) {
		this.userISEEDao = userISEEDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setISEEDao(ISEEDao iseeDao) {
		this.iseeDao = iseeDao;
	}
}
