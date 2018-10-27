package base.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import base.entities.*;

@Repository("logsDAO")
public class LogsDAOImpl implements LogsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Logs> findAll() {
		List<Logs> logs = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			logs = session.createQuery("from Logs order by id desc").list();
			transaction.commit();
		} catch (Exception e) {
			logs = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return logs;
	}

	@Override
	public void create(Logs log) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(log);
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
	public List<Logs> search(String keyword) {
		List<Logs> contacts = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			contacts = session.createQuery("select p from Logs p where p.description like :keyword order by p.id desc" )
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

	@Override
	public Logs find(int id) {
		Logs t = null;
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();		
			t = (Logs) session.createQuery("from Logs where id =:id" )
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
}
