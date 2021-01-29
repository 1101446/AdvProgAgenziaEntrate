package advprogproj.AgenziaEntrate.services;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advprogproj.AgenziaEntrate.model.dao.AccessDaoDefault;
import advprogproj.AgenziaEntrate.model.entities.Access;

@Service("access")
public class AccessServiceDefault implements AccessService{
	
	private AccessDaoDefault accessDao;
	
	@Transactional
	public Access findAccess(long id) {
		return this.accessDao.findById(id);
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
	public void delete(Access access) {
		this.accessDao.delete(access);
	}
}
