package advprogproj.AgenziaEntrate.services;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import advprogproj.AgenziaEntrate.model.entities.User;

import advprogproj.AgenziaEntrate.model.entities.Access;
import advprogproj.AgenziaEntrate.model.dao.UserDaoDefault;

@Service("userService")
public class UserServiceDefault implements UserService{
	
	private UserDaoDefault userDao;
	
	public User findById(String id) {
		return this.userDao.findById(id);
	}
	
	public User create(String cf, String firstName, String secondName, Date birthDate, String email, String password, boolean handicap, Access access) {
		return this.userDao.create(cf, firstName, secondName, birthDate, email, password, handicap, access);
	}
	
	public User update(User user) {
		return this.userDao.update(user);
	}
	
	public void delete(User user) {
		this.userDao.delete(user);
	}
	
}
