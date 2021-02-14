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
	void testAllCreatedAreFound() {
		
		int n = 5;		
		
		Session s = sf.openSession();
		
		accessDao.setSession(s);
		
		for(int i=0; i<n; i++) {
			accessDao.create("Ruolo"+i, i, "Descrizione"+i);
				
			List<Access> allAccess = accessDao.findAll();
				
			assertEquals(allAccess.size(),i+1);
		}
	}
	
	@Test
	void testAccessMustHaveRoleName() {
		
		Session s = sf.openSession();
		
		accessDao.setSession(s);
		
		assertThrows(Exception.class, () -> {accessDao.create("", 0, ""); });
	}
	
	@Test
	void testAccessMustHavePositivePriority() {
		
		Session s = sf.openSession();
		
		assertThrows(Exception.class, () -> {accessDao.create("Role", -1, ""); });
	}
}
