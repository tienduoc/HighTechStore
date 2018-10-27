package base.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import base.entities.*;

@Repository("paymentDAO")
public class PaymentDAOImpl implements PaymentDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Payment find(Integer id) {
		Payment t = null;
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			t = (Payment) session.createQuery("from Payment where id =:id" )
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
	public List<Payment> findAll() {
		List<Payment> payments = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			payments = session.createQuery("from Payment").list();
			transaction.commit();
		} catch (Exception e) {
			payments = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return payments;
	}

	@Override
	public void create(Payment payment) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(payment);
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
	public void delete(Payment payment) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.delete(payment);
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
	public void update(Payment payment) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.update(payment);
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
	public List<Payment> search(String keyword) {
		Query query = sessionFactory.getCurrentSession().createQuery("select c "
				+ "from Payment c "
				+ "where c.name like :keyword "				);
query.setString("keyword", "%" + keyword + "%");
return query.list() ;
	}

	@Override
	public Payment findByName(String name) {
		Payment t = null;
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			t = (Payment) session.createQuery("from Payment where name =:name" )
					.setString("name", name)
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
