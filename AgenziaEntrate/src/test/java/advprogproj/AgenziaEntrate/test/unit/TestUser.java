package advprogproj.AgenziaEntrate.test.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.test.DataServiceConfigTest;

public class TestUser {
	
	private AnnotationConfigApplicationContext ctx;
	private SessionFactory sf;
	private UserDao userDao;
	
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
		
		userDao = ctx.getBean("userDao", UserDao.class);
		
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
			
		userDao.setSession(s);
			
		assertEquals(s, userDao.getSession());
		assertTrue(userDao.getSession().getTransaction().isActive());
			
		s.getTransaction().commit();
			
		assertFalse(userDao.getSession().getTransaction().isActive());
	}
	
	@Test
	void testCreateUserDuplicateName() {
		
		Session s = sf.openSession();
		
		userDao.setSession(s);
		s.beginTransaction();
		
		User newUser1 = userDao.create("CODICEFISCALE", "Nome", "Cognome",LocalDate.of(1900,01,01), "email@email", "password", false, null);
		
		s.getTransaction().commit();	
		try {
			s.beginTransaction();
			
			User newUser2 = userDao.create(newUser1.getCf()+"AA", newUser1.getFirstName(), newUser1.getSecondName(), newUser1.getBirthD(), newUser1.getEmail()+"1email", newUser1.getPassword(), newUser1.isHandicap(), newUser1.getAccess());
			
			s.getTransaction().commit();
			assertTrue(true);
		}catch(Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	void testDoNotCreateUserDuplicateEmail() {
		
		Session s = sf.openSession();
		
		userDao.setSession(s);
		s.beginTransaction();
		
		User newUser1 = userDao.create("CODICEFISCALE", "Nome", "Cognome",LocalDate.of(1900,1,1), "email@email", userDao.encryptPassword("password"), false, null);
		
		s.getTransaction().commit();	
		try {
			s.beginTransaction();
			
			User newUser2 = userDao.create(newUser1.getCf()+"AA", newUser1.getFirstName(), newUser1.getSecondName(), newUser1.getBirthD(), newUser1.getEmail(), newUser1.getPassword(), newUser1.isHandicap(), null);
			
			s.getTransaction().commit();
			
			assertTrue(false);
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testNoUsersAtBeginning() {
		
		Session s = sf.openSession();
		
		userDao.setSession(s);
		
		List<User> allUsers = userDao.findAll();
			
		assertEquals(allUsers.size(),0);
	}
	
	@Test
	void testAllCreatedAreFoundAndFoundById() {
		
		int n = 5;		
		
		Session s = sf.openSession();
		
		userDao.setSession(s);
		
		for(int i=0; i<n; i++) {
			s.beginTransaction();
			
			User newUserI = userDao.create("CODICEFISCALEASD"+i, "Nome"+i, "Cognome"+i, LocalDate.of(1990,1,1), i+"email@email", userDao.encryptPassword("password"+i), false, null);
			
			s.getTransaction().commit();
			
			List<User> allUser = userDao.findAll();
				
			assertEquals(allUser.size(),i+1);
			
			User newUserF = userDao.findById(newUserI.getCf());
			
			assertSame(newUserI, newUserF);
			
		}
	}
	
	@Test
	void testUserMustHaveName() {
		
		Session s = sf.openSession();
		
		assertThrows(Exception.class, () -> {userDao.create("CODICEFISCALE", "", "",LocalDate.of(1900,01,01), "email@email", userDao.encryptPassword("password"), false, null); } );
	}
	
	@Test
	void testUserMustHaveBirthD() {
		
		Session s = sf.openSession();
		
		assertThrows(Exception.class, () -> {userDao.create("CODICEFISCALE", "Nome", "Cognome",null, "email@email", userDao.encryptPassword("password"), false, null); } );
	}
	
	@Test
	void testUserMustHaveEmail() {
		
		Session s = sf.openSession();
		
		assertThrows(Exception.class, () -> {userDao.create("CODICEFISCALE", "Nome", "Cognome",LocalDate.of(1900,01,01), "", "password", false, null); } );
	}
	
	@Test
	void testUserMustHavePassword() {
		
		Session s = sf.openSession();
		
		assertThrows(Exception.class, () -> {userDao.create("CODICEFISCALE", "Nome", "Cognome",LocalDate.of(1900,01,01), "email@email", "", false, null); } );
	}
	
	@Test
	void testUserNotFoundById() {
		Session s = sf.openSession();

		userDao.setSession(s);
		
		s.beginTransaction();
		
		User newUserI = userDao.create("CODICEFISCALE", "Nome", "Cognome",LocalDate.of(1900,01,01), "email@email", "password", false, null);
		
		s.getTransaction().commit();
		
		User UserF = userDao.findById(newUserI.getCf() + "ABC");
		
		assertNull(UserF);
	}
	
	@Test
	void testUserIsUpdatedCorrectlyWithMerging() {
		Session s = sf.openSession();

		userDao.setSession(s);
		
		s.beginTransaction();
		
		User userI = userDao.create("CODICEFISCALE", "Nome", "Cognome",LocalDate.of(1900,01,01), "email@email", "password", false, null);
		
		s.getTransaction().commit();
		
		User userU = new User();
		userU.setCf(userI.getCf());
		userU.setFirstName("Nome1");
		userU.setSecondName("Cognome1");
		userU.setBirthD(LocalDate.of(1901,01,01));
		userU.setEmail("email1@email1");
		userU.setPassword("Password1");
		userU.setHandicap(true);
		userDao.update(userU);
		
		s.beginTransaction();
		
		userU = userDao.update(userU);
		
		s.getTransaction().commit();
		
		User userF = userDao.findById(userI.getCf());
		
		assertSame(userI, userU);
		assertSame(userU, userF);
		assertSame(userF, userI);
	}

	
	@Test
	void testUserIsUpdatedCorrectlyWithoutMerging() {
		Session s = sf.openSession();

		userDao.setSession(s);

		s.beginTransaction();
		
		User userI = userDao.create("CODICEFISCALE", "Nome", "Cognome",LocalDate.of(1900,01,01), "email@email", "password", false, null);
		
		s.getTransaction().commit();
		
		User userU = new User();
		userU.setCf(userI.getCf());
		userU.setFirstName("Nome1");
		userU.setSecondName("Cognome1");
		userU.setBirthD(LocalDate.of(1901,01,01));
		userU.setEmail("email1@email1");
		userU.setPassword("Password1");
		userU.setHandicap(true);
		
		s.beginTransaction();
		
		userDao.update(userU);	
		
		s.getTransaction();
		User userF = userDao.findById(userI.getCf());
		
		assertNotNull(userF);
		assertEquals(userF, userI);
		assertSame(userF, userI);
		
		//assertEquals(found, updated);
		assertNotSame(userF, userU);
		
		assertEquals(userF.getFirstName(), userU.getFirstName());
		assertEquals(userF.getSecondName(), userU.getSecondName());
		assertEquals(userF.getBirthD(), userU.getBirthD());
		assertEquals(userF.getEmail(), userU.getEmail());
		assertEquals(userF.getPassword(), userU.getPassword());
		assertEquals(userF.isHandicap(), userU.isHandicap());
		
	}

	@Test
	void testUserIsCreatedAndDeleted() {
		Session s = sf.openSession();

		userDao.setSession(s);
		
		// 1. create a User
		s.beginTransaction();

		assertEquals(0, userDao.findAll().size());
		
		User userI = userDao.create("CODICEFISCALE", "Nome", "Cognome",LocalDate.of(1900,01,01), "email@email", "password", false, null);
		
		s.getTransaction().commit();
		
		// 2. delete the User
		s.beginTransaction();
		
		assertEquals(1, userDao.findAll().size());
		
		userDao.delete(userI);
		
		s.getTransaction().commit();
		
		// 3. check no more Users
		assertEquals(0, userDao.findAll().size());
		
	}
	
	@Test
	void testDeleteNonExistingUserDoesNotCauseError() {
		/**
		 * A User that does not exist can be deleted without begin noticed to the callee
		 * 
		 */
		Session s = sf.openSession();

		userDao.setSession(s);
				
		User fake = new User();
		fake.setCf("FAKE");
		
		assertNull(userDao.findById(fake.getCf()));
		
		try {
			userDao.delete(fake);
			assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception when deleting fake User");
		}
		
	}
	
	@Test
	void testJustCreatedUserOwnNothing() {
		Session s = sf.openSession();

		userDao.setSession(s);
		
		User userI = userDao.create("CODICEFISCALE", "Nome", "Cognome",LocalDate.of(1900,01,01), "email@email", "password", false, null);
		
		assertEquals(0, userI.getBankAccounts().size());
		assertEquals(0, userI.getUserRealEstates().size());
		assertEquals(0, userI.getUserVehicles().size());
		assertEquals(0, userI.getAssociatedISEEs().size());

	}
}
