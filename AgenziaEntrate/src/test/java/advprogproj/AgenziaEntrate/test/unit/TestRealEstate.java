package advprogproj.AgenziaEntrate.test.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import advprogproj.AgenziaEntrate.model.dao.RealEstateDao;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.test.DataServiceConfigTest;

public class TestRealEstate {
	
	private AnnotationConfigApplicationContext ctx;
	private SessionFactory sf;
	private RealEstateDao realEstateDao;
	
	@BeforeAll
	static void setup() {
		System.out.println("Preparazione ambiente");
	}
	
	@AfterAll
	static void tearDown() {
		System.out.println("Test conclusi");
	}
	
	@BeforeEach
	void openContext() {
		System.out.println("Caricamento ambiente");
		
		ctx =  new AnnotationConfigApplicationContext(DataServiceConfigTest.class);
		
		realEstateDao = ctx.getBean("realEstateDao", RealEstateDao.class);
		
		sf = ctx.getBean("sessionFactory", SessionFactory.class);
	}
	
	@AfterEach
	void closeContext() {
		System.out.println("Pulizia dell'ambiente in corso");
		
		ctx.close();
	}
	
	@Test
	void testBeginCommitTransaction() {
			
		Session s = sf.openSession();
			
		assertTrue(s.isOpen());
			
		s.beginTransaction();
			
		realEstateDao.setSession(s);
			
		assertEquals(s, realEstateDao.getSession());
		assertTrue(realEstateDao.getSession().getTransaction().isActive());
			
		s.getTransaction().commit();
			
		assertFalse(realEstateDao.getSession().getTransaction().isActive());
	}
	
	@Test
	void testDoNotCreateRealEstateDuplicateAddress() {
		
		Session s = sf.openSession();
		
		realEstateDao.setSession(s);
		
		RealEstate newRealEstate1 = realEstateDao.create("via1", "paese1", 10000);
			
		try {
			RealEstate newRealEstate2 = realEstateDao.create(newRealEstate1.getAddress(), newRealEstate1.getCountry(), newRealEstate1.getCAP());
			assertTrue(false);
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testNoRealEstatesAtBeginning() {
		
		Session s = sf.openSession();
		
		realEstateDao.setSession(s);
		
		List<RealEstate> allRealEstates = realEstateDao.findAll();
			
		assertEquals(allRealEstates.size(),0);
	}
	
	@Test
	void testAllCreatedAreFound() {
		
		int n = 5;		
		
		Session s = sf.openSession();
		
		realEstateDao.setSession(s);
		
		for(int i=0; i<n; i++) {
			realEstateDao.create("Via"+i, "Paese"+i, 10000+i);
				
			List<RealEstate> allAccess = realEstateDao.findAll();
				
			assertEquals(allAccess.size(),i+1);
		}
	}
}
