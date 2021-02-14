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

import advprogproj.AgenziaEntrate.model.dao.VehicleDao;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;
import advprogproj.AgenziaEntrate.test.DataServiceConfigTest;

public class TestVehicle {
	
	private AnnotationConfigApplicationContext ctx;
	private SessionFactory sf;
	private VehicleDao vehicleDao;
	
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
		
		vehicleDao = ctx.getBean("vehicleDao", VehicleDao.class);
		
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
			
		vehicleDao.setSession(s);
			
		assertEquals(s, vehicleDao.getSession());
		assertTrue(vehicleDao.getSession().getTransaction().isActive());
			
		s.getTransaction().commit();
			
		assertFalse(vehicleDao.getSession().getTransaction().isActive());
	}
	
	@Test
	void testDoNotCreateAccessDuplicateNames() {
		
		Session s = sf.openSession();
		
		vehicleDao.setSession(s);
		
		Vehicle newVehicle1 = vehicleDao.create("Marca1", "Modello1", "Targa1");
			
		try {
			Vehicle newVehicle2 = vehicleDao.create(newVehicle1.getBrand(),newVehicle1.getModel(),newVehicle1.getVehicleRegistration());
			assertTrue(false);
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testNoRolesAtBeginning() {
		
		Session s = sf.openSession();
		
		vehicleDao.setSession(s);
		
		List<Vehicle> allVehicles = vehicleDao.findAll();
			
		assertEquals(allVehicles.size(),0);
	}
	
	@Test
	void testAllCreatedAreFound() {
		
		int n = 5;		
		
		Session s = sf.openSession();
		
		vehicleDao.setSession(s);
		
		for(int i=0; i<n; i++) {
			vehicleDao.create("Modello"+i, "Marca"+i, "Targa"+i);
				
			List<Vehicle> allAccess = vehicleDao.findAll();
				
			assertEquals(allAccess.size(),i+1);
		}
	}
}
