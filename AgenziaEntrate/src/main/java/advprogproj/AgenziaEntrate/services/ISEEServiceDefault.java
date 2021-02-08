package advprogproj.AgenziaEntrate.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.ISEEDao;
import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.dao.UserDaoDefault;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.ISEE;
import advprogproj.AgenziaEntrate.model.entities.User;

@Service("ISEE")
public class ISEEServiceDefault implements ISEEService{
	
	private ISEEDao iseeDao;
	private UserDao userDao;
	
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
	public ISEE update(long id) {
		return this.iseeDao.update(this.findISEE(id));
	}
	
	@Transactional
	@Override
	public void delete(long id) {
		Set<User> users = this.iseeDao.getAssociatedUsers(this.findISEE(id));
		for(User u : users) {
			this.removeAssociatedUser(id, u.getCf());
		}
		this.iseeDao.delete(this.findISEE(id));
	}
	
	@Transactional
	@Override
	public void addAssociatedUser(long isee, String user) {
		ISEE i = this.findISEE(isee);
		User u = this.userDao.findById(user);
		this.iseeDao.addUserAssociated(u, i);
		this.userDao.update(u);
		this.iseeDao.update(i);
	}
	
	@Transactional
	@Override
	public void removeAssociatedUser(long isee, String user) {
		ISEE i = this.findISEE(isee);
		User u = this.userDao.findById(user);
		this.iseeDao.removeUserAssociated(u, i);
		this.userDao.update(u);
		this.iseeDao.update(i);
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
