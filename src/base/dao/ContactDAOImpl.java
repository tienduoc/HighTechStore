package base.dao;

import base.entities.*;

import java.util.List;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("contactDAO")
public class ContactDAOImpl implements ContactDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void create(Contact contact) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(contact);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> findAll() {
		List<Contact> contacts = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			contacts = session.createQuery("from Contact order by id desc").list();
			transaction.commit();
		} catch (Exception e) {
			contacts = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return contacts;
	}

		@Override
		public Contact find(Integer id) {
			Contact t = null;
			Transaction transaction = null;
			Session session = sessionFactory.openSession();
			try {
				transaction = session.beginTransaction();		
				t = (Contact) session.createQuery("from Contact where id =:id" )
						.setInteger("id", id)
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
	

	@Override
	public void delete(Contact contact) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.delete(contact);
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
	public void update(Contact contact) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.update(contact);
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
	public List<Contact> search(String keyword) {
		List<Contact> contacts = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			contacts = session.createQuery("select p from Contact p where p.name like :keyword")
					.setParameter("keyword", "%" + keyword + "%").list();
			transaction.commit();
		} catch (Exception e) {
			contacts = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return contacts;
	}
	
	
}
