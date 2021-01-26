package advprogproj.AgenziaEntrate.model.dao;

import java.util.Date;
import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.User;
import it.univpm.advprog.singers.model.dao.AlbumDao;
import it.univpm.advprog.singers.model.dao.DefaultDao;

public class UserDaoDefault extends DefaultDao implements UserDao{
	
	public User create(String cf, String firstName, String secondName, Date birthDate, String email, String password, boolean handicap = false) {
		return this.create(cf, firstName, secondName, birthDate, email, password, handicap, null);
	}
	
	public User create(User user) {}
	
	public User update(User user) {}
	
	public void delete(User user) {}
}
