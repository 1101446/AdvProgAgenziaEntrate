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
	
	@Override
	public List<BankAccount> findAllWithUser(){
		return this.getSession().createQuery("from BankAccount a join fetch a.owners", BankAccount.class).getResultList();
	}
	
	@Override
	public List<BankAccount> findAll(){
		return this.getSession().createQuery("from BankAccount a ", BankAccount.class).getResultList();
	}
	
	@Override
	public BankAccount findById(String IBAN, LocalDate billDate) {
		Query q = this.getSession().createQuery("from BankAccount a WHERE a.IBAN = :IBAN AND a.billDate = :billDate", BankAccount.class);
		return (BankAccount)q.setParameter("IBAN", IBAN).setParameter("billDate", billDate).getSingleResult();
	}
	
	@Override
	public Set<BankAccount> findByIBAN(String IBAN) {
		Query q = this.getSession().createQuery("from BankAccount a WHERE a.IBAN = :IBAN", BankAccount.class);
		return new HashSet<BankAccount>(q.setParameter("IBAN", IBAN).getResultList());
	}
	
	@Override
	public BankAccount create(String IBAN, String bankName, LocalDate billDate, long balance) {
		BankAccount bankAccount= new BankAccount();
		bankAccount.setIBAN(IBAN);
		bankAccount.setBankName(bankName);
		bankAccount.setBillDate(billDate);
		bankAccount.setBalance(balance);
		this.getSession().save(bankAccount);
		return bankAccount;
	}
	
	@Override
	public BankAccount update(BankAccount bankAccount) {
		return (BankAccount)this.getSession().merge(bankAccount);
	}
	
	@Override
	public void delete(BankAccount bankAccount) {
		this.getSession().delete(bankAccount);
	}
	
	@Override
	public void addOwner(User user, BankAccount bankAccount) {
		bankAccount.addOwner(user);
	}
	
	@Override
	public void removeOwner(User user, BankAccount bankAccount) {
		bankAccount.removeOwner(user);
	}
	
	@Override
	public Set<User> getOwners(BankAccount bankAccount){
		Query q = this.getSession().createQuery("from User a JOIN FETCH a.bankAccounts bk WHERE bk = :bankAccount", User.class);
		return new HashSet<User>(q.setParameter("bankAccount", bankAccount).getResultList());
	}
}
