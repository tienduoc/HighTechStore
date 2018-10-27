package base.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import base.entities.*;

@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(Orders order) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(order);
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
	public void update(Orders order) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.update(order);
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
	public Orders find(int id) {
		Orders orders = new Orders();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			orders = (Orders) session.createQuery("from Orders where id = :id").setInteger("id", id).uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			orders = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return orders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> findByUserName(String username) {
		List<Orders> orders = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			orders = session.createQuery("from Orders where accountByCustomer = :username order by id desc")
					.setMaxResults(20).setString("username", username).list();
			transaction.commit();
		} catch (Exception e) {
			orders = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return orders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> findAll() {
		List<Orders> orders = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			orders = session.createQuery("from Orders order by id desc").list();
			transaction.commit();
		} catch (Exception e) {
			orders = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return orders;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> search(String username) {
		List<Orders> orders = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			orders = session.createQuery("select p from Orders p where accountByCustomer.userName like :username")
					.setParameter("username", "%" + username + "%").list();
			transaction.commit();
		} catch (Exception e) {
			orders = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return orders;
	}

}
