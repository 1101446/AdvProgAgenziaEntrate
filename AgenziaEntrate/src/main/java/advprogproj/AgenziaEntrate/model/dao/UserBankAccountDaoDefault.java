package advprogproj.AgenziaEntrate.model.dao;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;
import javax.persistence.Query;

import advprogproj.AgenziaEntrate.model.entities.UserBankAccount;
import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.User;

@Repository("userBankAccountDao")
public class UserBankAccountDaoDefault extends DefaultDao implements UserBankAccountDao{
	
	@Override
	public Set<UserBankAccount> findAll() {
		Query q = this.getSession().createQuery("from UserBankAccount a join fetch a.user join fetch a.bankAccount", UserBankAccount.class);
		return new HashSet<UserBankAccount>(q.getResultList());
	}
	
	@Override
	public UserBankAccount findById(User user, BankAccount bankAccount) {
		Query q = this.getSession().createQuery("from UserBankAccount a join fetch a.user join fetch a.bankAccount "
				+ "WHERE a.user = :user AND a.bankAccount = :bankAccount", UserBankAccount.class);
		return (UserBankAccount) q.setParameter("user", user).setParameter("bankAccount", bankAccount).getSingleResult();
	}
	
	@Override
	public UserBankAccount create(User user, BankAccount bankAccount) {
		UserBankAccount userBankAccount = new UserBankAccount();
		userBankAccount.setUser(user);
		userBankAccount.setBankAccount(bankAccount);
		this.getSession().save(userBankAccount);
		user.addBankAccount(userBankAccount);
		bankAccount.addOwner(userBankAccount);
		return userBankAccount;
	}
	
	@Override
	public UserBankAccount update(UserBankAccount userBankAccount) {
		return (UserBankAccount)this.getSession().merge(userBankAccount);
	}
	
	@Override
	public void delete(UserBankAccount userBankAccount) {
		this.getSession().delete(userBankAccount);
	}
}
