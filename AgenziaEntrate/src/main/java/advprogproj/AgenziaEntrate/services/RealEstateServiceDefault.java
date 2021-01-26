package advprogproj.AgenziaEntrate.services;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;

public class RealEstateServiceDefault implements RealEstateService {
	
	public RealEstate findById(long id) {
		return this.getSession().find(RealEstate.class, id);
	}
	
	public RealEstate create(String address, String Country, long CAP) {
		RealEstate realEstate = new RealEstate();
		realEstate.setAddress(address);
		realEstate.setCountry(Country);
		realEstate.setCAP(CAP);
		this.getSession().save(realEstate);
		return realEstate;
	}
	
	public RealEstate update(RealEstate realEstate) {
		return (RealEstate)this.getSession().merge(realEstate);
	}
	
	public void delete(RealEstate realEstate) {
		this.getSession().delete(realEstate);
	}
	
	public Set<UserRealEstate> getUserRealEstate(RealEstate realEstate) {
		Query q = this.getSession().createQuery("from UserRealEstate a JOIN FETCH a.realEstate WHERE a.realEstate = :realEstate", UserRealEstate.class);
		return new HashSet<UserRealEstate>(q.setParameter("realEstate", realEstate).getResultList());
	}
}
