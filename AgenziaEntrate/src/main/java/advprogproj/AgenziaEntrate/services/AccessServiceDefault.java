package advprogproj.AgenziaEntrate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.AccessDao;
import advprogproj.AgenziaEntrate.model.entities.Access;

@Service("access")
public class AccessServiceDefault implements AccessService{
	
	private AccessDao accessDao;
	
	@Transactional
	@Override
	public List<Access> findAllAccess() {
		return this.accessDao.findAll();
	}
	
	@Transactional
	@Override
	public Access findAccess(long id) {
		return this.accessDao.findById(id);
	}
	
	@Transactional
	@Override
	public Access findAccessByName(String name) {
		return this.accessDao.findByName(name);
	}
	
	@Transactional
	@Override
	public Access create(String roleName, int priority, String description) {
		return this.accessDao.create(roleName, priority, description);
	}
	
	@Transactional
	@Override
	public Access update(Access access) {
		return this.accessDao.update(access);
	}
	
	@Transactional
	@Override
	public void delete(long id) {
		this.accessDao.delete(this.findAccess(id));
	}
	
	@Autowired
	public void setAccessDao(AccessDao accessDao) {
		this.accessDao = accessDao;
	}

}
