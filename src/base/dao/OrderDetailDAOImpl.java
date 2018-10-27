package base.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import base.entities.*;

@Repository("orderDetailDAO")
public class OrderDetailDAOImpl 
	implements OrderDetailDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(OrderDetail orderdetail) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(orderdetail);
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
	public List<OrderDetail> find(int id) {
		List<OrderDetail> details = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			details = session.createQuery("from OrderDetail where orders.id = :id")
					.setInteger("id", id).list();
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

}
