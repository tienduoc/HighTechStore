package base.dao;

import java.util.List;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import base.entities.*;

@Repository("supplierDAO")
public class SupplierDAOImpl implements SupplierDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Supplier find(Integer id) {
		Supplier t = null;
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			t = (Supplier) session.createQuery("from Supplier where id =:id order by id desc" )
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Supplier> findAll() {
		List<Supplier> suppliers = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			suppliers = session.createQuery("from Supplier").list();
			transaction.commit();
		} catch (Exception e) {
			suppliers = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return suppliers;
	}

	@Override
	public void create(Supplier supplier) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(supplier);
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
	public void delete(Supplier supplier) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.delete(supplier);
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
	public void update(Supplier supplier) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.update(supplier);
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
	public List<Supplier> search(String keyword) {
		List<Supplier> suppliers = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			suppliers = session.createQuery("select p from Supplier p where p.name like :keyword")
					.setParameter("keyword", "%" + keyword + "%").list();
			transaction.commit();
		} catch (Exception e) {
			suppliers = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return suppliers;
	}


	@Override
	public Supplier findByName(String name) {
	Supplier supplier = null;
	Session session = sessionFactory.openSession();
	Transaction transaction = null;
	try {
		transaction = session.beginTransaction();
		supplier = (Supplier) session.createQuery("from Supplier where name = :name ")
				.setString("name", name).uniqueResult();
		transaction.commit();
	} catch (Exception e) {
		supplier = null;
		if (transaction != null) {
			transaction.rollback();
		}
	} finally {
		session.close();
	}
	return supplier;
}
}
