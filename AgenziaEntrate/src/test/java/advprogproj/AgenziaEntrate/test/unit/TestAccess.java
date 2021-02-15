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

import advprogproj.AgenziaEntrate.model.dao.AccessDao;
import advprogproj.AgenziaEntrate.model.entities.Access;
import advprogproj.AgenziaEntrate.test.DataServiceConfigTest;

public class TestAccess {
	
	private AnnotationConfigApplicationContext ctx;
	private SessionFactory sf;
	private AccessDao accessDao;
	
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
		
		accessDao = ctx.getBean("accessDao", AccessDao.class);
		
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
			
		accessDao.setSession(s);
			
		assertEquals(s, accessDao.getSession());
		assertTrue(accessDao.getSession().getTransaction().isActive());
			
		s.getTransaction().commit();
			
		assertFalse(accessDao.getSession().getTransaction().isActive());
	}
	
	@Test
	void testDoNotCreateAccessDuplicateRoles() {
		
		Session s = sf.openSession();
		
		accessDao.setSession(s);
		
		Access newAccess1 = accessDao.create("ADMIN", 1, "Amministratore");
			
		try {
			Access newAccess2 = accessDao.create(newAccess1.getRoleName(), newAccess1.getPriority(), newAccess1.getDescription());
			assertTrue(false);
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testNoRolesAtBeginning() {
		
		Session s = sf.openSession();
		
		accessDao.setSession(s);
		
		List<Access> allAccess = accessDao.findAll();
			
		assertEquals(allAccess.size(),0);
	}
	
	@Test
	void testAllCreatedAreFoundAndFoundById() {
		
		int n = 5;		
		
		Session s = sf.openSession();
		
		accessDao.setSession(s);
		
		for(int i=0; i<n; i++) {
			Access newAccessI = accessDao.create("Ruolo"+i, i+1, "Descrizione"+i);
				
			List<Access> allAccess = accessDao.findAll();
			
			assertEquals(allAccess.size(),i+1);
			
			Access newAccessF = accessDao.findById(newAccessI.getId());
			
			assertSame(newAccessI, newAccessF);
		}
	}
	
	@Test
	void testAccessMustHaveRoleName() {
		
		Session s = sf.openSession();
		
		assertThrows(Exception.class, () -> {accessDao.create("", 0, ""); } );
	}
	
	@Test
	void testAccessMustHavePositivePriority() {
		
		Session s = sf.openSession();
		
		assertThrows(Exception.class, () -> {accessDao.create("Role", -1, ""); } );
	}
	
	@Test
	void testAccessNotFoundById() {
		Session s = sf.openSession();

		accessDao.setSession(s);

		Access newAccessI = accessDao.create("Role", 1, "");
		
		Access accessF = accessDao.findById(newAccessI.getId() + 10);
		
		assertNull(accessF);
	}
	
	@Test
	void testAccessIsUpdatedCorrectlyWithMerging() {
		Session s = sf.openSession();

		accessDao.setSession(s);

		Access accessI = accessDao.create("Role", 1, "");
		
		Access accessU = new Access();
		accessU.setId(accessI.getId());
		accessU.setRoleName("Role1");
		accessU.setPriority(2);
		
		accessU = accessDao.update(accessU);
		
		Access accessF = accessDao.findById(accessI.getId());
		
		assertSame(accessI, accessU);
		assertSame(accessU, accessF);
		assertSame(accessF, accessI);
	}

	
	@Test
	void testAccessIsUpdatedCorrectlyWithoutMerging() {
		Session s = sf.openSession();

		accessDao.setSession(s);

		Access accessI = accessDao.create("Role", 1, "");
		
		Access accessU = new Access();
		accessU.setId(accessI.getId());
		accessU.setRoleName("Role1");
		accessU.setPriority(2);
		
		accessU = accessDao.update(accessU);	
		
		Access accessF = accessDao.findById(accessI.getId());
		
		assertNotNull(accessF);
		assertEquals(accessF, accessI);
		assertSame(accessF, accessI);
		
		//assertEquals(found, updated);
		assertNotSame(accessF, accessU);
		
		assertEquals(accessF.getRoleName(), accessU.getRoleName());
		assertEquals(accessF.getPriority(), accessU.getPriority());
		assertEquals(accessF.getId(), accessU.getId());
		assertEquals(accessF.getDescription(), accessU.getDescription());
		
	}

	@Test
	void testAccessIsCreatedAndDeleted() {
		Session s = sf.openSession();

		accessDao.setSession(s);
		
		// 1. create a Access
		s.beginTransaction();

		assertEquals(0, accessDao.findAll().size());
		
		Access accessI = accessDao.create("Role", 1, "");
		
		s.getTransaction().commit();
		
		// 2. delete the Access
		s.beginTransaction();
		
		assertEquals(1, accessDao.findAll().size());
		
		accessDao.delete(accessI);
		
		s.getTransaction().commit();
		
		// 3. check no more Accesss
		assertEquals(0, accessDao.findAll().size());
		
	}
	
	@Test
	void testDeleteNonExistingAccessDoesNotCauseError() {
		/**
		 * A Access that does not exist can be deleted without begin noticed to the callee
		 * 
		 */
		Session s = sf.openSession();

		accessDao.setSession(s);
				
		Access fake = new Access();
		fake.setId(100L);
		
		assertNull(accessDao.findById(fake.getId()));
		
		try {
			accessDao.delete(fake);
			assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception when deleting fake Access");
		}
		
	}

}
