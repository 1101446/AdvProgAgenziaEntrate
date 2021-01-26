package advprogproj.AgenziaEntrate.model.dao;

import org.hibernate.Session;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;

public class RealEstateDaoDefault extends DefaultDao implements RealEstateDao {
	
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
}
