package base.dao;

import java.util.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import base.entities.*;

@Repository("producerDAO")
public class ProducerDAOImpl implements ProducerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Producer find(Integer id) {
		Producer t = null;
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			t = (Producer) session.createQuery("from Producer where id =:id order by id desc").setInteger("id", id).uniqueResult();
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
	public List<Producer> findAll() {
		List<Producer> producers = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			producers = session.createQuery("from Producer order by id desc").list();
			transaction.commit();
		} catch (Exception e) {
			producers = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return producers;
	}

	@Override
	public void create(Producer producer) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(producer);
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
	public void delete(Producer producer) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.delete(producer);
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
	public void update(Producer producer) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.update(producer);
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
	public List<Producer> search(String keyword) {
		List<Producer> producers = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			producers = session.createQuery("select p from Producer p where p.name like :keyword")
					.setParameter("keyword", "%" + keyword + "%").list();
			transaction.commit();
		} catch (Exception e) {
			producers = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return producers;
	}

	@Override
	public Producer findByName(String name) {
		Producer t = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			t = (Producer) session.createQuery("from Producer where name =:name").setString("name", name)
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
