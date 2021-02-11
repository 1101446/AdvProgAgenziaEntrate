package advprogproj.AgenziaEntrate.model.dao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.UserBankAccount;

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
	public void addOwner(BankAccount bankAccount, UserBankAccount userBankAccount) {
		bankAccount.addOwner(userBankAccount);
	}
	
	@Override
	public void removeOwner(BankAccount bankAccount, UserBankAccount userBankAccount) {
		bankAccount.removeOwner(userBankAccount);
	}
	
	@Override
	public Set<UserBankAccount> getOwners(BankAccount bankAccount){
		Query q = this.getSession().createQuery("from UserBankAccount a JOIN FETCH a.bankAccount WHERE a.bankAccount = :bankAccount", UserBankAccount.class);
		return new HashSet<UserBankAccount>(q.setParameter("bankAccount", bankAccount).getResultList());
	}
}
