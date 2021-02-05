package advprogproj.AgenziaEntrate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.AccessDaoDefault;
import advprogproj.AgenziaEntrate.model.entities.Access;

@Service("access")
public class AccessServiceDefault implements AccessService{
	
	private AccessDaoDefault accessDao;
	
	@Transactional
	public List<Access> findAllAccess() {
		return this.accessDao.findAll();
	}
	
	@Transactional
	public Access findAccess(long id) {
		return this.accessDao.findById(id);
	}
	
	@Transactional
	public Access findAccessByName(String name) {
		return this.accessDao.findByName(name);
	}
	
	@Transactional
	public Access create(String roleName, int priority, String description) {
		return this.accessDao.create(roleName, priority, description);
	}
	
	@Transactional
	public Access update(Access access) {
		return this.accessDao.update(access);
	}
	
	@Transactional
	public void delete(long id) {
		this.accessDao.delete(this.findAccess(id));
	}
	
	@Autowired
	public void setAccessDao(AccessDaoDefault accessDao) {
		this.accessDao = accessDao;
	}

}
