package base.dao;

import java.util.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import base.entities.*;

@Repository("accountDAO")
public class AccountDAOImpl implements AccountDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Account find(String username) {
		Account t = null;
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();		
			t = (Account) session.createQuery("from Account where username =:username" )
					.setString("username", username)
					.uniqueResult();		
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			t = null;
		} finally {
			session.flush();
			session.close();
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> findAll() {
		List<Account> accounts = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			accounts = session.createQuery("from Account").list();
			transaction.commit();
		} catch (Exception e) {
			accounts = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return accounts;
	}

	@Override
	public void create(Account account) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(account);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(Account account) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.delete(account);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.flush();
			session.close();
		}
	}

	@Override
	public void update(Account account) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.update(account);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> search(String keyword) {
		List<Account> accounts = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			accounts = session.createQuery("select p from Account p where p.userName like :keyword")
					.setParameter("keyword", "%" + keyword + "%").list();
			transaction.commit();
		} catch (Exception e) {
			accounts = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return accounts;
	}
}
