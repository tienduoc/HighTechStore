package base.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import base.entities.*;

@Repository("orderStatusDAO")
public class OrderStatusDAOImpl implements OrderStatusDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public OrderStatus find(Integer id) {
		return (OrderStatus) sessionFactory.getCurrentSession().get(OrderStatus.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderStatus> findAll() {
		List<OrderStatus> orderStatus = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			orderStatus = session.createQuery("from OrderStatus").list();
			transaction.commit();
		} catch (Exception e) {
			orderStatus = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return orderStatus;
	}

	@Override
	public OrderStatus findByName(String name) {
		OrderStatus t = null;		
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			t = (OrderStatus) session.createQuery("from OrderStatus where name =:name").setString("name", name)
					.uniqueResult();
			transaction.commit();
		} catch (RuntimeException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			t = null;
		} finally {
			session.close();
		}
		return t;
	}
	
}
