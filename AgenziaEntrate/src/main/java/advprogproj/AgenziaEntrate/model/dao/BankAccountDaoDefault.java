package advprogproj.AgenziaEntrate.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.User;

@Repository("bankAccountDao")
public class BankAccountDaoDefault extends DefaultDao implements BankAccountDao{
	
	public List<BankAccount> findAll(){
		return this.getSession().
				createQuery("from BankAccount a", BankAccount.class).
				getResultList();
	}
	
	public BankAccount findById(String IBAN) {
		return getSession().find(BankAccount.class, IBAN);
	}
	
	public BankAccount create(String IBAN, String bankName, Date billDate, long balance) {
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
}
