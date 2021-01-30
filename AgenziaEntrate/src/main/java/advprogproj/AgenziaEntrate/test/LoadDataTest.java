package advprogproj.AgenziaEntrate.test;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import advprogproj.AgenziaEntrate.app.DataServiceConfig;
import advprogproj.AgenziaEntrate.model.dao.DefaultDao;
import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.dao.UserDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.BankAccountDao;
import advprogproj.AgenziaEntrate.model.dao.BankAccountDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.RealEstateDao;
import advprogproj.AgenziaEntrate.model.dao.RealEstateDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.FamilyDao;
import advprogproj.AgenziaEntrate.model.dao.FamilyDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.AccessDao;
import advprogproj.AgenziaEntrate.model.dao.AccessDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.VehicleDao;
import advprogproj.AgenziaEntrate.model.dao.VehicleDaoDefault;
import advprogproj.AgenziaEntrate.model.entities.Access;
import advprogproj.AgenziaEntrate.model.dao.UserVehicleDao;
import advprogproj.AgenziaEntrate.model.dao.UserVehicleDaoDefault;
import advprogproj.AgenziaEntrate.model.dao.UserRealEstateDao;
import advprogproj.AgenziaEntrate.model.dao.UserRealEstateDaoDefault;

public class LoadDataTest {
	public static void main(String ... args) {
		//logger.info("Entrato ...");
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataServiceConfigTest.class)) {
	
			SessionFactory sf = ctx.getBean("sessionFactory", SessionFactory.class);
			
			UserDao userDao = ctx.getBean(UserDao.class);
			BankAccountDao bankAccountDao = ctx.getBean(BankAccountDao.class);
			RealEstateDao realEstateDao = ctx.getBean(RealEstateDao.class);
			VehicleDao vehicleDao = ctx.getBean(VehicleDao.class);
			AccessDao accessDao = ctx.getBean(AccessDao.class);
			FamilyDao familyDao = ctx.getBean(FamilyDao.class);
			UserRealEstateDao userRealEstateDao = ctx.getBean(UserRealEstateDao.class);
			UserVehicleDao userVehicleDao = ctx.getBean(UserVehicleDao.class);
			
			try (Session session = sf.openSession()) {
				
				userDao.setSession(session);
				bankAccountDao.setSession(session);
				realEstateDao.setSession(session);
				vehicleDao.setSession(session);
				accessDao.setSession(session);
				familyDao.setSession(session);
				userRealEstateDao.setSession(session);
				userVehicleDao.setSession(session);
				
				// Popolazione database
				
				session.beginTransaction();
				
				Access adminAccess = accessDao.create("Admin", 1, "Amministratore");
				Access aeAccess = accessDao.create("EnteAgenziaEntrate", 2, "Ente Agenzia Entrate");
				Access bccAccess = accessDao.create("EnteBCC", 3, "Ente Banca di Credito Cooperativo");
				Access piAccess = accessDao.create("EntePosteItaliane", 3, "Ente Poste Italiane");
				Access userAccess = accessDao.create("Cittadino", 4, "Cittadino");
				
				session.getTransaction().commit();
				
				session.beginTransaction();
				
				vehicleDao.create("Fiat","Panda","AA000AA");
				vehicleDao.create("Audi","A3","AA001AA");
				vehicleDao.create("Alfa Romeo","Brera","AA002AA");
				vehicleDao.create("Yamaha","GTX950","AA003AA");
				vehicleDao.create("Enterprise","3080Ti","AA004AA");
	
				realEstateDao.create("Via Roma, 99 Ancona","Italia",60080);
				realEstateDao.create("Via X Settembre, 38 Macerata","Italia",61001);
				realEstateDao.create("Via Flaminia, 101 Urbino","Italia",60180);
				realEstateDao.create("Via Roma, 99 Roma","Italia",60000);
				realEstateDao.create("Via II Dicembre, 1 Milano","Italia",60025);
				
				LocalDate billDate2015 = LocalDate.of(2015,12,31);
				LocalDate billDate2016 = LocalDate.of(2016,12,31);
				LocalDate billDate2017 = LocalDate.of(2017,12,31);
				LocalDate billDate2018 = LocalDate.of(2018,12,31);
				LocalDate billDate2019 = LocalDate.of(2019,12,31);
				
				bankAccountDao.create("IT01A0000000000000000000000","BCC Roma",billDate2018,15000);
				bankAccountDao.create("IT01A0000000000000000000000","BCC Roma",billDate2019,12500);
				bankAccountDao.create("IT10B0003330002200000000444","Intesa San Paolo Milano",billDate2015,30000);
				bankAccountDao.create("IT10B0003330002200000000444","Intesa San Paolo Milano",billDate2016,40000);
				bankAccountDao.create("IT10B0003330002200000000444","Intesa San Paolo Milano",billDate2017,20000);
				bankAccountDao.create("IT05G0003430005200000000999","Monte dei Paschi Siena",billDate2017,10000);
				bankAccountDao.create("IT05G0003430005200000000999","Monte dei Paschi Siena",billDate2018,20500);
				bankAccountDao.create("IT05G0003430005200000000999","Monte dei Paschi Siena",billDate2019,15000);
				
				session.getTransaction().commit();
				
				session.beginTransaction();

				userDao.create("AAABBB20A78L700X", "Mario", "Rossi", LocalDate.of(1970, 5, 22), "mariorossi@libero.it", userDao.encryptPassword("mario"), false, userAccess);
				userDao.create("ABABAB78B14T880I", "Mario", "Rossi", LocalDate.of(1980, 3, 20), "mario.rossi@gmail.com", userDao.encryptPassword("giovanni"), false, adminAccess);
				userDao.create("CGGCGC80A72L598X", "Giovanni", "Belardi", LocalDate.of(1950, 5, 22), "giovannibelardi@hotmail.it", userDao.encryptPassword("paolo"), false, aeAccess);
				userDao.create("REERTY79A92L354X", "Paolo", "Bianchi", LocalDate.of(1960, 5, 22), "p.bianchi@yahoo.com", userDao.encryptPassword("gatto"), true, userAccess);
				userDao.create("JOPFRT45A58L667X", "Pino", "Insegna", LocalDate.of(1975, 5, 22), "insegna.p@gmailcom", userDao.encryptPassword("cane"), false, piAccess);
				
				session.getTransaction().commit();
				/*Singer rw = singerDao.create("Roger", "Waters", LocalDate.of(1963, 9, 6));
				Singer mj = singerDao.create("Michael", "Jackson", null);
							
				albumDao.create("Wish you where here", rw);
				
				albumDao.create("Thriller", mj);
				
				assert mj.getAlbums().size() == 0;
				assert rw.getAlbums().size() == 0;
				
				// fai refresh per ricaricare
				session.refresh(mj);
				session.refresh(rw);
				
				assert mj.getAlbums().size() == 1;
				assert rw.getAlbums().size() == 1;
				
				Album tdb = albumDao.create("The division bell", rw);
				tdb.setSinger(rw);
				albumDao.update(tdb);
				
			
				Instrument i1 = instrumentDao.findByName("Stratocaster");
				Instrument i2 = instrumentDao.findByName("Moog");
				Instrument i3 = instrumentDao.findByName("Stradivari");
							
				session.getTransaction().commit();
				
				session.beginTransaction();
	
				rw.addInstrument(i1);
				rw = singerDao.update(rw);
	
				assert(rw.getInstruments().contains(i1));
				assert(i1.getSingers().contains(rw));
				
				session.getTransaction().commit();
				
				session.beginTransaction();
	
				
				rw.addInstrument(i2);
				rw = singerDao.update(rw);
				
				assert rw.getInstruments().contains(i2);
				assert i2.getSingers().contains(rw);
				
				session.getTransaction().commit();
	
				session.beginTransaction();
	
				mj.addInstrument(i2);
				mj.addInstrument(i3);
				mj = singerDao.update(mj);
				
				assert mj.getInstruments().contains(i2) == true;
				assert mj.getInstruments().contains(i3) == true;
				assert i2.getSingers().contains(mj);
				assert i3.getSingers().contains(mj);
				
				session.getTransaction().commit();
				
				session.beginTransaction();
	
				// rimuovi tutte le entita` collegate a quella da eliminare
				mj.getInstruments().clear();
				for (Album a : mj.getAlbums()) {
					albumDao.delete(a);
				}
				mj.getAlbums().clear();
				mj = singerDao.update(mj);
	
				// elimina l'entita`
				singerDao.delete(mj);
	
				session.getTransaction().commit();
				
				session.beginTransaction();
	
				// phase 2 : navigate data in the database
				
				List<Singer> all = singerDao.findAll();
				
				System.out.println("Number of singers: " + all.size());
				for (Singer s : all) {
					System.out.println(" - " + s.getFullName() + " : " + s.getBirthDate());
					
					Set<Album> albums = singerDao.getAlbums(s);
					System.out.println("Number of albums: " + albums.size());
					for (Album a : albums) {
						System.out.println("  - " + a.getTitle());					
					}
				}
				
				List<Instrument> allInstruments = instrumentDao.findAll();
				System.out.println("Number of instruments: " + allInstruments.size());
				for (Instrument i : allInstruments) {
					System.out.println(" - " + i.getFamily() + " : " + i.getName());
					Set<Singer> singers = i.getSingers();
					
					if (singers == null) {
						singers = new HashSet<Singer>();
					}
					
					System.out.println("Number of singers: " + singers.size());
					for (Singer s : singers) {
						System.out.println("  - " + s.getFullName());
					}
				}
				
				session.getTransaction().commit();
				
				// phase 3 : create user
				session.beginTransaction();
				
				Role r1 = roleDao.create("USER");
				Role r2 = roleDao.create("ADMIN");
				
				session.getTransaction().commit();
				
				session.beginTransaction();
				
				User u1 = userDao.create("user1", userDao.encryptPassword("user1"), true);				
				u1.addRole(r1);
				
				User u2 = userDao.create("user2", userDao.encryptPassword("user2"), true);
				u2.addRole(r1);
				u2.addRole(r2);
				
				userDao.update(u1);
				userDao.update(u2);
				session.getTransaction().commit();*/
				System.out.println("HALLO");
			}catch (Exception e) {
		//		logger.error("Eccezione: " + e.getMessage());
				e.printStackTrace(System.err);
			}
//	logger.info("Esco ...");
		}
	}
}
