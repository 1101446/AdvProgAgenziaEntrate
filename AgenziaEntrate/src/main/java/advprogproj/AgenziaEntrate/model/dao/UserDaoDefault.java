package advprogproj.AgenziaEntrate.model.dao;

import java.util.Date;
import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.dao.DefaultDao;

public class UserDaoDefault extends DefaultDao implements UserDao{
	
	public User findById(String id) {
		return getSession().find(User.class, id);
	}
	
	public User create(String cf, String firstName, String secondName, Date birthDate, String email, String password, boolean handicap) {
		User user = new User();
		user.setCf(cf);
		user.setFirstName(firstName);
		user.setSecondName(secondName);
		user.setBirthD(birthDate);
		user.setEmail(email);
		user.setPassword(password);
		user.setHandicap(handicap);
		this.getSession().save(user);
		return user;
	}
	
	public User update(User user) {
		return (User)this.getSession().merge(user);
	}
	
	public void delete(User user) {
		this.getSession().delete(user);
	}
}
