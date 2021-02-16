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

import advprogproj.AgenziaEntrate.model.dao.BankAccountDao;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.test.DataServiceConfigTest;

public class TestBankAccount {
	
	private AnnotationConfigApplicationContext ctx;
	private SessionFactory sf;
	private BankAccountDao bankAccountDao;
	
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
		
		bankAccountDao = ctx.getBean("bankAccountDao", BankAccountDao.class);
		
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
			
		bankAccountDao.setSession(s);
			
		assertEquals(s, bankAccountDao.getSession());
		assertTrue(bankAccountDao.getSession().getTransaction().isActive());
			
		s.getTransaction().commit();
			
		assertFalse(bankAccountDao.getSession().getTransaction().isActive());
	}
	
	@Test
	void testCreateBankAccountDuplicateBankNameAndBillDate() {
		
		Session s = sf.openSession();
		
		bankAccountDao.setSession(s);
		
		s.beginTransaction();
		
		BankAccount newBankAccount1 = bankAccountDao.create("IBAN", "Bank", LocalDate.of(1900, 12, 31), 1000);
		
		s.getTransaction().commit();
		
		try {
			s.beginTransaction();
			
			BankAccount newBankAccount2 = bankAccountDao.create("IBAN2",newBankAccount1.getBankName(), newBankAccount1.getBillDate(), newBankAccount1.getBalance());
			
			s.getTransaction().commit();
			assertTrue(true);
		}catch(Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	void testNoBankAccountsAtBeginning() {
		
		Session s = sf.openSession();
		
		bankAccountDao.setSession(s);
		
		List<BankAccount> allBankAccounts = bankAccountDao.findAll();
			
		assertEquals(allBankAccounts.size(),0);
	}
	
	@Test
	void testAllCreatedAreFoundAndFoundById() {
		
		int n = 5;		
		
		Session s = sf.openSession();
		
		bankAccountDao.setSession(s);
		
		for(int i=0; i<n; i++) {
			s.beginTransaction();
			
			BankAccount newBankAccountI = bankAccountDao.create("IBAN"+i, "Bank"+i, LocalDate.of(1900+i, 12, 31), 1000+i);
			
			s.getTransaction().commit();
			
			List<BankAccount> allBankAccount = bankAccountDao.findAll();
				
			assertEquals(allBankAccount.size(),i+1);
			
			BankAccount newBankAccountF = bankAccountDao.findById(newBankAccountI.getIBAN(),newBankAccountI.getBillDate());
			
			assertSame(newBankAccountI, newBankAccountF);
			
		}
	}
	
	@Test
	void testBankAccountMustHaveBankName() {
		
		Session s = sf.openSession();
		
		assertThrows(Exception.class, () -> {bankAccountDao.create("IBAN", "", LocalDate.of(1900, 12, 31), 1000); } );
	}
	
	@Test
	void testBankAccountMustHaveCorrectBalance() {
		
		Session s = sf.openSession();
		
		assertThrows(Exception.class, () -> {bankAccountDao.create("IBAN", "Bank", LocalDate.of(1900, 12, 31), -1); } );
	}
	
	@Test
	void testBankAccountNotFoundById() {
		Session s = sf.openSession();

		bankAccountDao.setSession(s);

		BankAccount newBankAccountI = bankAccountDao.create("IBAN", "Bank", LocalDate.of(1900, 12, 31), 10000);
		
		assertThrows(Exception.class, () -> { bankAccountDao.findById(newBankAccountI.getIBAN() + "AAA", LocalDate.of(1900,12,31)); });
	}
	
	@Test
	void testBankAccountIsUpdatedCorrectlyWithMerging() {
		Session s = sf.openSession();

		bankAccountDao.setSession(s);
		
		s.beginTransaction();
		
		BankAccount bankAccountI = bankAccountDao.create("IBAN", "Bank", LocalDate.of(1900, 12, 31), 10000);
		
		s.getTransaction().commit();
		
		BankAccount bankAccountU = new BankAccount();
		bankAccountU.setIBAN(bankAccountI.getIBAN());
		bankAccountU.setBillDate(bankAccountI.getBillDate());
		bankAccountU.setBankName("Bank1");
		bankAccountU.setBalance(11000);
		
		s.beginTransaction();
		
		bankAccountU = bankAccountDao.update(bankAccountU);
		
		s.getTransaction().commit();
		
		BankAccount bankAccountF = bankAccountDao.findById(bankAccountI.getIBAN(), bankAccountI.getBillDate());
		
		assertSame(bankAccountI, bankAccountU);
		assertSame(bankAccountU, bankAccountF);
		assertSame(bankAccountF, bankAccountI);
	}

	
	@Test
	void testBankAccountIsUpdatedCorrectlyWithoutMerging() {
		Session s = sf.openSession();

		bankAccountDao.setSession(s);

		s.beginTransaction();
		
		BankAccount bankAccountI = bankAccountDao.create("IBAN", "Bank", LocalDate.of(1900, 12, 31), 10000);
		
		s.getTransaction().commit();		
		BankAccount bankAccountU = new BankAccount();
		bankAccountU.setIBAN(bankAccountI.getIBAN());
		bankAccountU.setBillDate(bankAccountI.getBillDate());
		bankAccountU.setBankName("Bank1");
		bankAccountU.setBalance(11000);
		
		s.beginTransaction();
		
		bankAccountDao.update(bankAccountU);	
		
		s.getTransaction().commit();
		
		BankAccount bankAccountF = bankAccountDao.findById(bankAccountI.getIBAN(), bankAccountI.getBillDate());
		
		assertNotNull(bankAccountF);
		assertEquals(bankAccountF, bankAccountI);
		assertSame(bankAccountF, bankAccountI);
		
		//assertEquals(found, updated);
		assertNotSame(bankAccountF, bankAccountU);
		
		assertEquals(bankAccountF.getBankName(), bankAccountU.getBankName());
		assertEquals(bankAccountF.getBalance(), bankAccountU.getBalance());
		assertEquals(bankAccountF.getIBAN(), bankAccountU.getIBAN());
		assertEquals(bankAccountF.getBillDate(), bankAccountU.getBillDate());
		
	}

	@Test
	void testBankAccountIsCreatedAndDeleted() {
		Session s = sf.openSession();

		bankAccountDao.setSession(s);
		
		// 1. create a BankAccount
		s.beginTransaction();

		assertEquals(0, bankAccountDao.findAll().size());
		
		BankAccount bankAccountI = bankAccountDao.create("IBAN", "Bank", LocalDate.of(1900, 12, 31), 10000);
		
		s.getTransaction().commit();
		
		// 2. delete the BankAccount
		s.beginTransaction();
		
		assertEquals(1, bankAccountDao.findAll().size());
		
		bankAccountDao.delete(bankAccountI);
		
		s.getTransaction().commit();
		
		// 3. check no more BankAccounts
		assertEquals(0, bankAccountDao.findAll().size());
		
	}
	
	@Test
	void testDeleteNonExistingBankAccountDoesNotCauseError() {
		/**
		 * A BankAccount that does not exist can be deleted without begin noticed to the callee
		 * 
		 */
		Session s = sf.openSession();

		bankAccountDao.setSession(s);
				
		BankAccount fake = new BankAccount();
		fake.setIBAN("ASASSD11000L");
		fake.setBillDate(LocalDate.of(1900, 12, 31));
		
		assertThrows(Exception.class, () -> {bankAccountDao.findById(fake.getIBAN(),fake.getBillDate());});
		
		try {
			bankAccountDao.delete(fake);
			assertTrue(true);
		} catch (Exception e) {
			fail("Unexpected exception when deleting fake BankAccount");
		}
		
	}
	
	@Test
	void testJustCreatedBankAccountHasNoOwners() {
		Session s = sf.openSession();

		bankAccountDao.setSession(s);
		
		BankAccount bankAccountI = bankAccountDao.create("IBAN", "Bank", LocalDate.of(1900, 12, 31), 10000);
		
		assertEquals(0, bankAccountI.getOwners().size());

	}
}
