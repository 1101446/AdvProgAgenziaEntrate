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
	void testDoNotCreateVehicleDuplicateAttributes() {
		
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
	void testAllCreatedAreFoundAndFoundById() {
		
		int n = 5;		
		
		Session s = sf.openSession();
		
		vehicleDao.setSession(s);
		
		for(int i=0; i<n; i++) {
			
			Vehicle newVehicleI = vehicleDao.create("Marca"+i, "Modello"+i, "Targa"+i);
				
			List<Vehicle> allAccess = vehicleDao.findAll();
				
			assertEquals(allAccess.size(),i+1);
			
			Vehicle newVehicleF = vehicleDao.findById(newVehicleI.getId());
			
			assertSame(newVehicleI, newVehicleF);
		}
	}
	
	@Test
	void testVehicleMustHaveBrand() {
		
		Session s = sf.openSession();
		
		assertThrows(Exception.class, () -> {vehicleDao.create("", "Modello", "Targa"); } );
	}
	
	@Test
	void testVehicleMustHaveModel() {
		
		Session s = sf.openSession();
		
		assertThrows(Exception.class, () -> {vehicleDao.create("Marca", "", "Targa"); } );
	}
	
	@Test
	void testVehicleMustHaveVehicleRegistration() {
		
		Session s = sf.openSession();
		
		assertThrows(Exception.class, () -> {vehicleDao.create("Marca", "Modello", ""); } );
	}
	
	@Test
	void testVehicleNotFoundById() {
		Session s = sf.openSession();

		vehicleDao.setSession(s);

		Vehicle newVehicleI = vehicleDao.create("Marca", "Modello", "Targa");
		
		Vehicle VehicleF = vehicleDao.findById(newVehicleI.getId() + 10);
		
		assertNull(VehicleF);
	}
	
	@Test
	void testVehicleIsUpdatedCorrectlyWithMerging() {
		Session s = sf.openSession();

		vehicleDao.setSession(s);

		Vehicle vehicleI = vehicleDao.create("Marca","Modello", "Targa");
		
		Vehicle vehicleU = new Vehicle();
		vehicleU.setId(vehicleI.getId());
		vehicleU.setBrand("Marca1");
		vehicleU.setModel("Modello1");
		vehicleU.setVehicleRegistration("Targa1");
		
		vehicleU = vehicleDao.update(vehicleU);
		
		Vehicle vehicleF = vehicleDao.findById(vehicleI.getId());
		
		assertSame(vehicleI, vehicleU);
		assertSame(vehicleU, vehicleF);
		assertSame(vehicleF, vehicleI);
	}

	
	@Test
	void testVehicleIsUpdatedCorrectlyWithoutMerging() {
		Session s = sf.openSession();

		vehicleDao.setSession(s);

		Vehicle vehicleI = vehicleDao.create("Marca","Modello", "Targa");
		
		Vehicle vehicleU = new Vehicle();
		vehicleU.setId(vehicleI.getId());
		vehicleU.setBrand("Marca1");
		vehicleU.setModel("Modello1");
		vehicleU.setVehicleRegistration("Targa1");
		
		vehicleDao.update(vehicleU);	
		
		Vehicle vehicleF = vehicleDao.findById(vehicleI.getId());
		
		assertNotNull(vehicleF);
		assertEquals(vehicleF, vehicleI);
		assertSame(vehicleF, vehicleI);
		
		//assertEquals(found, updated);
		assertNotSame(vehicleF, vehicleU);
		
		assertEquals(vehicleF.getBrand(), vehicleU.getBrand());
		assertEquals(vehicleF.getModel(), vehicleU.getModel());
		assertEquals(vehicleF.getId(), vehicleU.getId());
		assertEquals(vehicleF.getVehicleRegistration(), vehicleU.getVehicleRegistration());
		
	}

	@Test
	void testVehicleIsCreatedAndDeleted() {
		Session s = sf.openSession();

		vehicleDao.setSession(s);
		
		// 1. create a Vehicle
		s.beginTransaction();

		assertEquals(0, vehicleDao.findAll().size());
		
		Vehicle vehicleI = vehicleDao.create("Marca","Modello", "Targa");
		
		s.getTransaction().commit();
		
		// 2. delete the Vehicle
		s.beginTransaction();
		
		assertEquals(1, vehicleDao.findAll().size());
		
		vehicleDao.delete(vehicleI);
		
		s.getTransaction().commit();
		
		// 3. check no more Vehicles
		assertEquals(0, vehicleDao.findAll().size());
		
	}
	
	@Test
	void testDeleteNonExistingVehicleDoesNotCauseError() {
		/**
		 * A Vehicle that does not exist can be deleted without begin noticed to the callee
		 * 
		 */
		Session s = sf.openSession();

		vehicleDao.setSession(s);
				
		Vehicle fake = new Vehicle();
		fake.setId(100L);
		
		assertNull(vehicleDao.findById(fake.getId()));
		
		try {
			vehicleDao.delete(fake);
			assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception when deleting fake Vehicle");
		}
		
	}
	
	@Test
	void testJustCreatedVehicleHasNoOwners() {
		Session s = sf.openSession();

		vehicleDao.setSession(s);
		
		Vehicle vehicleI = vehicleDao.create("Marca","Modello", "Targa");
		
		assertEquals(0, vehicleI.getOwners().size());

	}
}
