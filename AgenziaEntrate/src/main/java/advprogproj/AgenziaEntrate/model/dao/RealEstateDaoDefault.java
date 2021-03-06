package advprogproj.AgenziaEntrate.model.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;

@Repository("realEstateDao")
public class RealEstateDaoDefault extends DefaultDao implements RealEstateDao {
	
	@Override
	public RealEstate findById(long id) {
		return this.getSession().find(RealEstate.class, id);
	}
	
	@Override
	public List<RealEstate> findAll(){
		return this.getSession().createQuery("from RealEstate a", RealEstate.class).getResultList();
	}
	
	@Override
	public RealEstate create(String address, String country, int CAP) {
		RealEstate realEstate = new RealEstate();
		realEstate.setAddress(address);
		realEstate.setCountry(country);
		realEstate.setCAP(CAP);
		this.getSession().save(realEstate);
		return realEstate;
	}
	
	@Override
	public RealEstate update(RealEstate realEstate) {
		return (RealEstate)this.getSession().merge(realEstate);
	}
	
	@Override
	public void delete(RealEstate realEstate) {
		this.getSession().delete(realEstate);
	}
	
	@Override
	public Set<UserRealEstate> getUserRealEstate(RealEstate realEstate) {
		Query q = this.getSession().createQuery("from UserRealEstate a JOIN FETCH a.realEstate WHERE a.realEstate = :realEstate", UserRealEstate.class);
		return new HashSet<UserRealEstate>(q.setParameter("realEstate", realEstate).getResultList());
	}
	
	@Override
	public void addUserRealEstate(RealEstate realEstate, UserRealEstate userRealEstate) {
		realEstate.addOwner(userRealEstate);
	}
	
	@Override
	public void removeUserRealEstate(RealEstate realEstate, UserRealEstate userRealEstate) {
		realEstate.removeOwner(userRealEstate);
	}
}
