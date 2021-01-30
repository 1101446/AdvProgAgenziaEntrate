package advprogproj.AgenziaEntrate.model.dao;

import java.time.LocalDate;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;

@Repository("userVehicleDao")
public class UserVehicleDaoDefault extends DefaultDao implements UserVehicleDao{
	
	public UserVehicle findById(User user, Vehicle vehicle, LocalDate date) {
		Query q = this.getSession().createQuery("from UserVehicle a join fetch a.user join fetch a.vehicle "
				+ "WHERE a.user = :user AND a.userVehicle = :userVehicle AND a.endOfYear = :date", UserVehicle.class);
		return (UserVehicle) q.setParameter("user", user).
				setParameter("realEstate", vehicle).
				setParameter("date", date).getSingleResult();
	}
	
	public UserVehicle create(User user, Vehicle vehicle, LocalDate endOfYear, long price) {
		UserVehicle userVehicle = new UserVehicle();
		userVehicle.setUser(user);
		userVehicle.setVehicle(vehicle);
		userVehicle.setEndOfYear(endOfYear);
		userVehicle.setPrice(price);
		this.getSession().save(userVehicle);
		return userVehicle;
	}
	
	public UserVehicle update(UserVehicle userVehicle) {
		return (UserVehicle)this.getSession().merge(userVehicle);
	}
	
	public void delete(UserVehicle userVehicle) {
		this.getSession().delete(userVehicle);
	}
}
