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
	void testAllCreatedAreFoundAndFoundById() {
		
		int n = 5;		
		
		Session s = sf.openSession();
		
		realEstateDao.setSession(s);
		
		for(int i=0; i<n; i++) {
			RealEstate newRealEstateI = realEstateDao.create("Via"+i, "Paese"+i, 10000+i);
				
			List<RealEstate> allRealEstate = realEstateDao.findAll();
				
			assertEquals(allRealEstate.size(),i+1);
			
			RealEstate newRealEstateF = realEstateDao.findById(newRealEstateI.getId());
			
			assertSame(newRealEstateI, newRealEstateF);
			
		}
	}
	
	@Test
	void testRealEstateMustHaveAddress() {
		
		Session s = sf.openSession();
		
		assertThrows(Exception.class, () -> {realEstateDao.create("", "Paese", 0); } );
	}
	
	@Test
	void testRealEstateMustHaveCountry() {
		
		Session s = sf.openSession();
		
		assertThrows(Exception.class, () -> {realEstateDao.create("Via", "", 10000); } );
	}
	
	@Test
	void testRealEstateNotFoundById() {
		Session s = sf.openSession();

		realEstateDao.setSession(s);

		RealEstate newRealEstateI = realEstateDao.create("Via", "Paese", 10000);
		
		RealEstate RealEstateF = realEstateDao.findById(newRealEstateI.getId() + 10);
		
		assertNull(RealEstateF);
	}
	
	@Test
	void testRealEstateIsUpdatedCorrectlyWithMerging() {
		Session s = sf.openSession();

		realEstateDao.setSession(s);

		RealEstate realEstateI = realEstateDao.create("Via", "Paese", 10000);
		
		RealEstate realEstateU = new RealEstate();
		realEstateU.setId(realEstateI.getId());
		realEstateU.setAddress("Via1");
		realEstateU.setAddress("Paese1");
		realEstateU.setCAP(10001);
		
		realEstateU = realEstateDao.update(realEstateU);
		
		RealEstate realEstateF = realEstateDao.findById(realEstateI.getId());
		
		assertSame(realEstateI, realEstateU);
		assertSame(realEstateU, realEstateF);
		assertSame(realEstateF, realEstateI);
	}

	
	@Test
	void testRealEstateIsUpdatedCorrectlyWithoutMerging() {
		Session s = sf.openSession();

		realEstateDao.setSession(s);

		RealEstate realEstateI = realEstateDao.create("Via", "Paese", 10000);
		
		RealEstate realEstateU = new RealEstate();
		realEstateU.setId(realEstateI.getId());
		realEstateU.setAddress("Via1");
		realEstateU.setAddress("Paese1");
		realEstateU.setCAP(10001);
		
		realEstateDao.update(realEstateU);	
		
		RealEstate realEstateF = realEstateDao.findById(realEstateI.getId());
		
		assertNotNull(realEstateF);
		assertEquals(realEstateF, realEstateI);
		assertSame(realEstateF, realEstateI);
		
		//assertEquals(found, updated);
		assertNotSame(realEstateF, realEstateU);
		
		assertEquals(realEstateF.getAddress(), realEstateU.getAddress());
		assertEquals(realEstateF.getCountry(), realEstateU.getCountry());
		assertEquals(realEstateF.getId(), realEstateU.getId());
		assertEquals(realEstateF.getCAP(), realEstateU.getCAP());
		
	}

	@Test
	void testRealEstateIsCreatedAndDeleted() {
		Session s = sf.openSession();

		realEstateDao.setSession(s);
		
		// 1. create a RealEstate
		s.beginTransaction();

		assertEquals(0, realEstateDao.findAll().size());
		
		RealEstate realEstateI = realEstateDao.create("Via", "Paese", 10000);
		
		s.getTransaction().commit();
		
		// 2. delete the RealEstate
		s.beginTransaction();
		
		assertEquals(1, realEstateDao.findAll().size());
		
		realEstateDao.delete(realEstateI);
		
		s.getTransaction().commit();
		
		// 3. check no more RealEstates
		assertEquals(0, realEstateDao.findAll().size());
		
	}
	
	@Test
	void testDeleteNonExistingRealEstateDoesNotCauseError() {
		/**
		 * A RealEstate that does not exist can be deleted without begin noticed to the callee
		 * 
		 */
		Session s = sf.openSession();

		realEstateDao.setSession(s);
				
		RealEstate fake = new RealEstate();
		fake.setId(100L);
		
		assertNull(realEstateDao.findById(fake.getId()));
		
		try {
			realEstateDao.delete(fake);
			assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception when deleting fake RealEstate");
		}
		
	}
	
	@Test
	void testJustCreatedRealEstateHasNoOwners() {
		Session s = sf.openSession();

		realEstateDao.setSession(s);
		
		RealEstate realEstateI = realEstateDao.create("Via", "Paese", 10000);
		
		assertEquals(0, realEstateI.getOwners().size());

	}
}
