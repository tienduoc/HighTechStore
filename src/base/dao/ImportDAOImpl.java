package base.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import base.entities.*;

@Repository("importDAO")

public class ImportDAOImpl implements ImportDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void update(Imports imports) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.update(imports);
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
	public Imports find(Integer id) {
		Imports imports = new Imports();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			imports = (Imports) session.createQuery("from Imports where id = :id").setInteger("id", id).uniqueResult();
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
	public List<Imports> findByCheckMan(String username) {
		List<Imports> imports = null;
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
	public List<Imports> findAll() {
		List<Imports> imports = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			imports = session.createQuery("from Imports order by id desc").list();
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
	public void create(Imports imports) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(imports);
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

