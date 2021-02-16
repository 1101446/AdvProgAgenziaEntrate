package advprogproj.AgenziaEntrate.test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import advprogproj.AgenziaEntrate.app.DataServiceConfig;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.model.entities.UserBankAccount;
import advprogproj.AgenziaEntrate.model.entities.UserISEE;
import advprogproj.AgenziaEntrate.model.entities.UserRealEstate;
import advprogproj.AgenziaEntrate.model.entities.UserVehicle;
import advprogproj.AgenziaEntrate.model.entities.Vehicle;
import advprogproj.AgenziaEntrate.model.dao.UserDao;
import advprogproj.AgenziaEntrate.model.dao.BankAccountDao;
import advprogproj.AgenziaEntrate.model.dao.RealEstateDao;
import advprogproj.AgenziaEntrate.model.dao.FamilyDao;
import advprogproj.AgenziaEntrate.model.dao.ISEEDao;
import advprogproj.AgenziaEntrate.model.dao.AccessDao;
import advprogproj.AgenziaEntrate.model.dao.VehicleDao;
import advprogproj.AgenziaEntrate.model.entities.Access;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.Family;
import advprogproj.AgenziaEntrate.model.entities.ISEE;
import advprogproj.AgenziaEntrate.model.entities.RealEstate;
import advprogproj.AgenziaEntrate.model.dao.UserVehicleDao;
import advprogproj.AgenziaEntrate.model.dao.UserRealEstateDao;
import advprogproj.AgenziaEntrate.model.dao.UserBankAccountDao;
import advprogproj.AgenziaEntrate.model.dao.UserISEEDao;

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
			UserBankAccountDao userBankAccountDao = ctx.getBean(UserBankAccountDao.class);
			ISEEDao iseeDao = ctx.getBean(ISEEDao.class);
			UserISEEDao userISEEDao = ctx.getBean(UserISEEDao.class);
			
			try (Session session = sf.openSession()) {
				
				userDao.setSession(session);
				bankAccountDao.setSession(session);
				realEstateDao.setSession(session);
				vehicleDao.setSession(session);
				accessDao.setSession(session);
				familyDao.setSession(session);
				userRealEstateDao.setSession(session);
				userVehicleDao.setSession(session);
				iseeDao.setSession(session);
				userBankAccountDao.setSession(session);
				userISEEDao.setSession(session);
				
				// Popolazione database
				
				session.beginTransaction();
				
				Access adminAccess = accessDao.create("ADMIN", 1, "Amministratore");
				Access aeAccess = accessDao.create("ENTRATE", 2, "Ente Agenzia Entrate");
				Access bccAccess = accessDao.create("ENTE_CREDITO", 3, "Ente Banca di Credito Cooperativo");
				Access catAccess = accessDao.create("CATASTO", 3, "Ente Catasto");
				Access motoAccess = accessDao.create("MOTORIZZAZIONE", 3, "Ente motorizzazione");
				Access userAccess = accessDao.create("UTENTE", 4, "Cittadino");
				
				session.getTransaction().commit();		
				session.beginTransaction();
				
				Vehicle veicoloMario = vehicleDao.create("Fiat","Panda","AA000AA");
				Vehicle veicoloPaolo = vehicleDao.create("Audi","A3","AA001AA");
				Vehicle veicoloMarioPaolo = vehicleDao.create("Alfa Romeo","Brera","AA002AA");
				vehicleDao.create("Yamaha","GTX950","AA003AA");
				vehicleDao.create("Enterprise","3080Ti","AA004AA");
	
				RealEstate casaRossi = realEstateDao.create("Via Roma, 99 Ancona","Italia",60080);
				RealEstate casaBianchi = realEstateDao.create("Via X Settembre, 38 Macerata","Italia",61001);
				RealEstate casaRossiBianchi = realEstateDao.create("Via Flaminia, 101 Urbino","Italia",60180);
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
				
				bankAccountDao.create("IT01A0000000000000000000001","BCC Roma",billDate2018,15000);
				bankAccountDao.create("IT01A0000000000000000000001","BCC Roma",billDate2019,12500);
				bankAccountDao.create("IT10B0003330002200000000443","Intesa San Paolo Milano",billDate2017,30000);
				bankAccountDao.create("IT10B0003330002200000000443","Intesa San Paolo Milano",billDate2018,40000);
				bankAccountDao.create("IT10B0003330002200000000443","Intesa San Paolo Milano",billDate2019,20000);
				bankAccountDao.create("IT05G0003430005200000000991","Monte dei Paschi Siena",billDate2017,10000);
				bankAccountDao.create("IT05G0003430005200000000991","Monte dei Paschi Siena",billDate2018,20500);
				bankAccountDao.create("IT05G0003430005200000000991","Monte dei Paschi Siena",billDate2019,15000);
				
				session.getTransaction().commit();
				session.beginTransaction();
				
				userDao.create("ADMINAD0A00A000A", "admin", "admin", LocalDate.of(1970, 5, 22), "admin@admin.com", userDao.encryptPassword("admin"), false, adminAccess);
				userDao.create("AAABBB20A78L700X", "Mario", "Rossi", LocalDate.of(1970, 5, 22), "mariorossi@libero.it", userDao.encryptPassword("mario"), false, userAccess);
				userDao.create("ABABAB78B14T880I", "Mario", "Rossi", LocalDate.of(1980, 3, 20), "mario.rossi@gmail.com", userDao.encryptPassword("giovanni"), false, adminAccess);
				userDao.create("CGGCGC80A72L598X", "Giovanni", "Belardi", LocalDate.of(1950, 5, 22), "giovannibelardi@hotmail.it", userDao.encryptPassword("paolo"), false, aeAccess);
				userDao.create("REERTY79A92L354X", "Paolo", "Bianchi", LocalDate.of(1960, 5, 22), "p.bianchi@yahoo.com", userDao.encryptPassword("gatto"), true, userAccess);
				userDao.create("JOPFRT45A58L667X", "Pino", "Insegna", LocalDate.of(1975, 5, 22), "insegna.p@gmail.com", userDao.encryptPassword("cane"), false, bccAccess);
				userDao.create("MRCRSS10L79M480X", "Marco", "Rossi", LocalDate.of(2010, 4, 29), "marco.rossi@gmail.com", userDao.encryptPassword("marco"), false, userAccess);
				userDao.create("LCBNCH05E11L344Y", "Luca", "Bianchi", LocalDate.of(2005, 8, 22), "l.bianchi@yahoo.com", userDao.encryptPassword("luca"), true, userAccess);
				
				session.getTransaction().commit();
				session.beginTransaction();
				
				userDao.create("CDDCDFF0A00A000A", "Giovanni", "Verdi", LocalDate.of(1960, 5, 22), "g.verdi@gmail.com", userDao.encryptPassword("giovanni"), false, userAccess);
				userDao.create("XSSD4DS0A78L700X", "Cristina", "Giorgietti", LocalDate.of(1970, 5, 22), "c.giorgietti@gmail.com", userDao.encryptPassword("cristina"), false, userAccess);
				userDao.create("ABABAB78B14T880Z", "Giacomo", "Verdi", LocalDate.of(1995, 3, 20), "gia.verdi@gmail.com", userDao.encryptPassword("giacomo"), true, userAccess);
				userDao.create("XYZXYZ80A72L598X", "Margherita", "Verdi", LocalDate.of(1997, 5, 22), "m.verdi@gmail.com", userDao.encryptPassword("margherita"), false, userAccess);
				
				userDao.create("987FDSKHAKKNVLSD", "Gigi", "Proietti", LocalDate.of(1940, 11, 2), "g.proietti@gmail.com", userDao.encryptPassword("gigi"), true, userAccess);
				userDao.create("348SDFSDJFSAAJSB", "Cinzia", "Giustini", LocalDate.of(1960, 5, 22), "c.giustini@gmail.com", userDao.encryptPassword("cinzia"), false, userAccess);
				userDao.create("78AFNSDSF789ASDA", "Laura", "Proietti", LocalDate.of(1980, 4, 29), "l.proietti@gmail.com", userDao.encryptPassword("laura"), false, userAccess);
				userDao.create("947SDFKJS97ASFDF", "Cristian", "Proietti", LocalDate.of(1990, 8, 22), "c.proietti@gmail.com", userDao.encryptPassword("cristian"), true, userAccess);
				
				session.getTransaction().commit();
				session.beginTransaction();
				
				User mario = userDao.findByEmail("mariorossi@libero.it");
				User paolo = userDao.findByEmail("p.bianchi@yahoo.com");
				User marco = userDao.findByEmail("marco.rossi@gmail.com");
				User luca = userDao.findByEmail("l.bianchi@yahoo.com");
				
				User verdi1 = userDao.findByEmail("g.verdi@gmail.com");
				User verdi2 = userDao.findByEmail("c.giorgietti@gmail.com");
				User verdi3 = userDao.findByEmail("gia.verdi@gmail.com");
				User verdi4 = userDao.findByEmail("m.verdi@gmail.com");
				
				User proietti1 = userDao.findByEmail("g.proietti@gmail.com");
				User proietti2 = userDao.findByEmail("c.giustini@gmail.com");
				User proietti3 = userDao.findByEmail("l.proietti@gmail.com");
				User proietti4 = userDao.findByEmail("c.proietti@gmail.com");
				
				BankAccount bankAccount1 = bankAccountDao.findById("IT01A0000000000000000000000", billDate2018);
				BankAccount bankAccount2 = bankAccountDao.findById("IT01A0000000000000000000000", billDate2019);
				BankAccount bankAccount3 = bankAccountDao.findById("IT05G0003430005200000000999", billDate2018);
				BankAccount bankAccount4 = bankAccountDao.findById("IT05G0003430005200000000999", billDate2019);
				
				BankAccount bankAccountV1 = bankAccountDao.findById("IT01A0000000000000000000001",billDate2018);
				BankAccount bankAccountV2 = bankAccountDao.findById("IT01A0000000000000000000001", billDate2019);
				BankAccount bankAccountV3 = bankAccountDao.findById("IT10B0003330002200000000443", billDate2018);
				BankAccount bankAccountV4 = bankAccountDao.findById("IT10B0003330002200000000443", billDate2019);
				
				BankAccount bankAccountP1 = bankAccountDao.findById("IT05G0003430005200000000991",billDate2017);
				BankAccount bankAccountP2 = bankAccountDao.findById("IT05G0003430005200000000991",billDate2018);
				BankAccount bankAccountP3 = bankAccountDao.findById("IT05G0003430005200000000991",billDate2019);
				
				session.getTransaction().commit();		
				session.beginTransaction();
				
				UserBankAccount marioBankAccount1 = userBankAccountDao.create(mario, bankAccount1);
				UserBankAccount marioBankAccount2 = userBankAccountDao.create(mario, bankAccount2);
				
				UserBankAccount verdiBankAccount1 = userBankAccountDao.create(verdi1, bankAccountV1);
				UserBankAccount verdiBankAccount2 = userBankAccountDao.create(verdi1, bankAccountV2);
				
				UserBankAccount verdiBankAccount3 = userBankAccountDao.create(verdi2, bankAccountV1);
				UserBankAccount verdiBankAccount4 = userBankAccountDao.create(verdi2, bankAccountV2);
				
				UserBankAccount verdiBankAccount5 = userBankAccountDao.create(verdi3, bankAccountV3);
				
				UserBankAccount verdiBankAccount8 = userBankAccountDao.create(verdi4, bankAccountV4);
				
				UserBankAccount proiettiBankAccount1 = userBankAccountDao.create(proietti1, bankAccountP1);
				UserBankAccount proiettiBankAccount2 = userBankAccountDao.create(proietti1, bankAccountP2);
				
				UserBankAccount proiettiBankAccount3 = userBankAccountDao.create(proietti2, bankAccountP1);
				UserBankAccount proiettiBankAccount4 = userBankAccountDao.create(proietti2, bankAccountP2);
				
				UserBankAccount proiettiBankAccount5 = userBankAccountDao.create(proietti3, bankAccountP3);
				
				UserBankAccount proiettiBankAccount8 = userBankAccountDao.create(proietti4, bankAccountP3);
				
				assert(mario.getBankAccounts().contains(marioBankAccount1));
				assert(mario.getBankAccounts().contains(marioBankAccount2));
				
				session.getTransaction().commit();		
				session.beginTransaction();
				
				UserBankAccount paoloBankAccount2 = userBankAccountDao.create(paolo, bankAccount2);
				UserBankAccount paoloBankAccount3 = userBankAccountDao.create(paolo, bankAccount3);
				UserBankAccount paoloBankAccount4 = userBankAccountDao.create(paolo, bankAccount4);
				
				assert (bankAccount1.getOwners().contains(marioBankAccount1));
				assert (bankAccount2.getOwners().contains(paoloBankAccount2));
				assert (bankAccount2.getOwners().contains(marioBankAccount2));
				assert (bankAccount3.getOwners().contains(paoloBankAccount3));
				assert (bankAccount4.getOwners().contains(paoloBankAccount4));
				
				session.getTransaction().commit();			
				session.beginTransaction();
				
				UserRealEstate marioCasaRossi = userRealEstateDao.create(mario, casaRossi, billDate2019, 14000);
				UserRealEstate paoloCasaBianchi = userRealEstateDao.create(paolo, casaBianchi, billDate2019, 20000);
				UserRealEstate marioCasaRossiBianchi = userRealEstateDao.create(mario, casaRossiBianchi, billDate2019, 15000);
				UserRealEstate paoloCasaRossiBianchi = userRealEstateDao.create(paolo, casaRossiBianchi, billDate2019, 15000);
				
				assert (mario.getUserRealEstates().contains(marioCasaRossi));
				assert (paolo.getUserRealEstates().contains(paoloCasaBianchi));
				assert (mario.getUserRealEstates().contains(marioCasaRossiBianchi));
				assert (paolo.getUserRealEstates().contains(paoloCasaRossiBianchi));
				
				assert (casaRossi.getOwners().contains(marioCasaRossi));
				assert (casaBianchi.getOwners().contains(paoloCasaBianchi));
				assert (casaRossiBianchi.getOwners().contains(marioCasaRossiBianchi));
				assert (casaRossiBianchi.getOwners().contains(paoloCasaRossiBianchi));
				
				userDao.update(mario);
				userDao.update(paolo);
				
				realEstateDao.update(casaRossi);
				realEstateDao.update(casaBianchi);
				realEstateDao.update(casaRossiBianchi);
				
				session.getTransaction().commit();	
				session.beginTransaction();
				
				UserVehicle marioVeicoloMario = userVehicleDao.create(mario, veicoloMario, billDate2019, 5000);
				UserVehicle paoloVeicoloPaolo = userVehicleDao.create(paolo, veicoloPaolo, billDate2019, 10000);
				UserVehicle marioVeicoloMarioPaolo = userVehicleDao.create(mario, veicoloMarioPaolo, billDate2018, 7000);
				UserVehicle paoloVeicoloMarioPaolo = userVehicleDao.create(paolo, veicoloMarioPaolo, billDate2018, 7000);
				
				assert (mario.getUserVehicles().contains(marioVeicoloMario));
				assert (paolo.getUserVehicles().contains(paoloVeicoloPaolo));
				assert (mario.getUserVehicles().contains(marioVeicoloMarioPaolo));
				assert (paolo.getUserVehicles().contains(paoloVeicoloMarioPaolo));
				
				assert (veicoloMario.getOwners().contains(marioVeicoloMario));
				assert (veicoloPaolo.getOwners().contains(paoloVeicoloPaolo));
				assert (veicoloMarioPaolo.getOwners().contains(marioVeicoloMarioPaolo));
				assert (veicoloMarioPaolo.getOwners().contains(paoloVeicoloMarioPaolo));
				
				userDao.update(mario);
				userDao.update(paolo);
				
				vehicleDao.update(veicoloMario);
				vehicleDao.update(veicoloPaolo);
				vehicleDao.update(veicoloMarioPaolo);
				
				session.getTransaction().commit();
				session.beginTransaction();
				
				familyDao.create(1, mario, "Genitore", "Mario Rossi");
				familyDao.create(1, marco, "Figlio", "Mario Rossi");
				familyDao.create(2, paolo, "Genitore", "Paolo Bianchi");
				familyDao.create(1, luca, "Figlio", "Paolo Bianchi");
				
				familyDao.create(3, proietti1, "Genitore", "Gigi Proietti");
				familyDao.create(3, proietti2, "Genitore", "Gigi Proietti");
				familyDao.create(3, proietti3, "Figlio", "Gigi Proietti");
				familyDao.create(3, proietti4, "Figlio", "Gigi Proietti");
				
				familyDao.create(4, verdi1, "Genitore", "Giovanni Verdi");
				familyDao.create(4, verdi2, "Genitore", "Giovanni Verdi");
				familyDao.create(4, verdi3, "Figlio", "Giovanni Verdi");
				familyDao.create(4, verdi4, "Figlio", "Giovanni Verdi");
				
				session.getTransaction().commit();
				session.beginTransaction();
				
				ISEE iseeMario2018 = iseeDao.create(2018, 22000);
				ISEE iseePaolo2018 = iseeDao.create(2018, 30000);
				
				session.getTransaction().commit();		
				session.beginTransaction();
				
				UserISEE marioISEE2018 = userISEEDao.create(mario, iseeMario2018);
				UserISEE marcoISEE2018 = userISEEDao.create(marco, iseeMario2018);
				UserISEE paoloISEE2018 = userISEEDao.create(paolo, iseePaolo2018);
				UserISEE lucaISEE2018 = userISEEDao.create(luca, iseePaolo2018);
				
				assert (mario.getAssociatedISEEs().contains(marioISEE2018));
				assert (marco.getAssociatedISEEs().contains(marcoISEE2018));
				
				assert (paolo.getAssociatedISEEs().contains(paoloISEE2018));
				assert (luca.getAssociatedISEEs().contains(lucaISEE2018));
				
				assert (iseeMario2018.getAssociatedUsers().contains(marioISEE2018));
				assert (iseeMario2018.getAssociatedUsers().contains(marcoISEE2018));
				
				assert (iseePaolo2018.getAssociatedUsers().contains(paoloISEE2018));
				assert (iseePaolo2018.getAssociatedUsers().contains(lucaISEE2018));
				
				userDao.update(mario);
				userDao.update(marco);
				userDao.update(paolo);
				userDao.update(luca);
				
				session.getTransaction().commit();
				session.beginTransaction();
				
				ISEE iseeMario2019 = iseeDao.create(2019, 22000);
				ISEE iseePaolo2019 = iseeDao.create(2019, 30000);
				
				session.getTransaction().commit();		
				session.beginTransaction();
				
				UserISEE marioISEE2019 = userISEEDao.create(mario, iseeMario2019);
				UserISEE marcoISEE2019 = userISEEDao.create(marco, iseeMario2019);
				UserISEE paoloISEE2019 = userISEEDao.create(paolo, iseePaolo2019);
				UserISEE lucaISEE2019 = userISEEDao.create(luca, iseePaolo2019);
				
				assert (mario.getAssociatedISEEs().contains(marioISEE2019));
				assert (marco.getAssociatedISEEs().contains(marcoISEE2019));
				
				assert (paolo.getAssociatedISEEs().contains(paoloISEE2019));
				assert (luca.getAssociatedISEEs().contains(lucaISEE2019));
				
				assert (iseeMario2019.getAssociatedUsers().contains(marioISEE2019));
				assert (iseeMario2019.getAssociatedUsers().contains(marcoISEE2019));
				
				assert (iseePaolo2019.getAssociatedUsers().contains(paoloISEE2019));
				assert (iseePaolo2019.getAssociatedUsers().contains(lucaISEE2019));
				
				userDao.update(mario);
				userDao.update(marco);
				userDao.update(paolo);
				userDao.update(luca);
				
				session.getTransaction().commit();
				session.beginTransaction();
				//rimuovo solo i riferimenti alel altre entità, i conti correnti verrano poi intestati a
				//un altro utente
				for(UserBankAccount ubk : mario.getBankAccounts()) {
					ubk.getBankAccount().removeOwner(ubk);
					userBankAccountDao.delete(ubk);
				}
				
				for(UserISEE ui : mario.getAssociatedISEEs()) {
					ui.getIsee().removeAssociatedUser(ui);
					userISEEDao.delete(ui);
				}
				
				for(UserRealEstate ure : mario.getUserRealEstates()) {
					ure.getRealEstate().removeOwner(ure);
					userRealEstateDao.delete(ure);
				}
				
				for(UserVehicle uv : mario.getUserVehicles()) {
					uv.getVehicle().removeOwner(uv);
					userVehicleDao.delete(uv);
				}
				
				mario.getBankAccounts().clear();
				mario.getAssociatedISEEs().clear();
				mario.getUserRealEstates().clear();
				mario.getUserVehicles().clear();

				for(Family f : userDao.getFamilies(mario)) {
					familyDao.delete(f);
				}
				
				assert mario.getBankAccounts().size() == 0;
				assert mario.getAssociatedISEEs().size() == 0;
				assert mario.getUserRealEstates().size() == 0;
				assert mario.getUserVehicles().size() == 0;
				
				userDao.delete(mario);
				
				session.getTransaction().commit();
				session.beginTransaction();
				
				for(UserRealEstate ure : casaRossiBianchi.getOwners()) {
					ure.getUser().removeUserRealEstate(ure);
					userRealEstateDao.delete(ure);
				}
				
				realEstateDao.delete(casaRossiBianchi);
				
				for(UserVehicle uv : veicoloMarioPaolo.getOwners()) {
					uv.getUser().removeUserVehicle(uv);
					userVehicleDao.delete(uv);
				}
				
				vehicleDao.delete(veicoloMarioPaolo);
				
				session.getTransaction().commit();
				session.beginTransaction();
				
				for(UserBankAccount ubk : bankAccount2.getOwners()) {
					ubk.getUser().removeBankAccount(ubk);
					userBankAccountDao.delete(ubk);
				}
				
				bankAccountDao.delete(bankAccount2);
				
				for(UserISEE ui : iseeMario2019.getAssociatedUsers()) {
					ui.getUser().removeAssociatedISEE(ui);
					userISEEDao.delete(ui);
				}
				
				iseeDao.delete(iseeMario2019);
				
				session.getTransaction().commit();
				// phase 2 : navigate data in the database
				
				List<User> allUser = userDao.findAll();
				System.out.println("Numero utenti: " + allUser.size());
				for (User u : allUser) {
					System.out.println(u.getFirstName() + " " + u.getSecondName() + ":" + u.getBirthD());
					
					Set<UserBankAccount> bankAccounts = userDao.getBankAccounts(u);
					System.out.println("Conti correnti: " + bankAccounts.size());
					for (UserBankAccount ubk : bankAccounts) {
						System.out.println("- " + ubk.getBankAccount().getBankName()+ ": " 
										   + ubk.getBankAccount().getIBAN() + " Data Saldo:" + ubk.getBankAccount().getBillDate() + " Saldo:"
										   + ubk.getBankAccount().getBalance());					
					}
					Set<UserRealEstate> realEstates = userDao.getUserRealEstates(u);
					System.out.println("Immobili: " + realEstates.size());
					for (UserRealEstate ure : realEstates) {
						System.out.println("- " + ure.getRealEstate().getAddress() + " " 
										   + ure.getRealEstate().getCAP() + " " + ure.getRealEstate().getCountry()
										   + " " + ure.getEndOfYear() + " " + ure.getPrice());					
					}
					
					Set<UserVehicle> vehicles = userDao.getUserVehicles(u);
					System.out.println("Veicoli: " + vehicles.size());
					for (UserVehicle uv : vehicles) {
						System.out.println("- " + uv.getVehicle().getBrand() + " " + uv.getVehicle().getModel()
										   + " " + uv.getVehicle().getVehicleRegistration() + " "
										   + uv.getEndOfYear() + " " + uv.getPrice());					
					}
					
					Set<Family> families = userDao.getFamilies(u);
					System.out.println("Famiglie: " + families.size());
					for (Family f : families) {
						System.out.println("- Gerarchia: " + f.getHierarchy() + 
										   " Capo famiglia: " + f.getHouseHolder());
					}
				}
			}catch (Exception e) {
				e.printStackTrace(System.err);
			}
//	logger.info("Esco ...");
		}
	}
}
