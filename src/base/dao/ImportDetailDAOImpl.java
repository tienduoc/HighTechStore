package base.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import base.entities.*;

@Repository("importDetailDAO")

public class ImportDetailDAOImpl implements ImportDetailDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<ImportDetail> find(int id) {
		List<ImportDetail> details = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			details = session.createQuery("from ImportDetail where importId = :id").setInteger("id", id).list();
			transaction.commit();
		} catch (Exception e) {
			details = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return details;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImportDetail> findByCheckMan(String username) {
		List<ImportDetail> imports = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			imports = session.createQuery("from Imports where accountByCheckMan = :username import by id desc")
					.setMaxResults(20).setString("username", username).list();
			transaction.commit();
		} catch (Exception e) {
			imports = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return imports;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImportDetail> findAll() {
		List<ImportDetail> imports = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			imports = session.createQuery("from ImportDetail").list();
			transaction.commit();
		} catch (Exception e) {
			imports = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return imports;
	}

	@Override
	public void create(ImportDetail importDetail) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(importDetail);
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
	}
}
