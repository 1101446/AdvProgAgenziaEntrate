package advprogproj.AgenziaEntrate.model.dao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.User;

@Repository("bankAccountDao")
public class BankAccountDaoDefault extends DefaultDao implements BankAccountDao{
	
	public List<BankAccount> findAll(){
		return this.getSession().createQuery("from BankAccount a", BankAccount.class).getResultList();
	}
	
	public BankAccount findById(String IBAN, LocalDate billDate) {
		Query q = this.getSession().createQuery("from BankAccount a WHERE a.IBAN = :IBAN AND a.billDate = :billDate", BankAccount.class);
		return (BankAccount)q.setParameter("IBAN", IBAN).setParameter("billDate", billDate).getSingleResult();
	}
	
	public Set<BankAccount> findByIBAN(String IBAN) {
		Query q = this.getSession().createQuery("from BankAccount a WHERE a.IBAN = :IBAN", BankAccount.class);
		return new HashSet<BankAccount>(q.setParameter("IBAN", IBAN).getResultList());
	}
	
	public BankAccount create(String IBAN, String bankName, LocalDate billDate, long balance) {
		BankAccount bankAccount= new BankAccount();
		bankAccount.setIBAN(IBAN);
		bankAccount.setBankName(bankName);
		bankAccount.setBillDate(billDate);
		bankAccount.setBalance(balance);
		this.getSession().save(bankAccount);
		return bankAccount;
	}
	
	public BankAccount update(BankAccount bankAccount) {
		return (BankAccount)this.getSession().merge(bankAccount);
	}
	
	public void delete(BankAccount bankAccount) {
		this.getSession().delete(bankAccount);
	}
	
	public void addOwner(User user, BankAccount bankAccount) {
		bankAccount.addOwner(user);
	}
	
	public void removeOwner(User user, BankAccount bankAccount) {
		bankAccount.removeOwner(user);
	}
	
	public Set<User> getOwners(BankAccount bankAccount){
		Query q = this.getSession().createQuery("from User a JOIN FETCH a.bankAccounts WHERE a.bankAccounts = :bankAccount", User.class);
		return new HashSet<User>(q.setParameter("bankAccount", bankAccount).getResultList());
	}
	
}
