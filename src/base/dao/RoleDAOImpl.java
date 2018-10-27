package base.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import base.entities.Role;

@Repository("roleDAO")
public class RoleDAOImpl implements RoleDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAll() {
		List<Role> roles = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			roles = session.createQuery("from Role").list();
			transaction.commit();
		} catch (Exception e) {
			roles = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return roles;
	}

	@Override
	public Role find(Integer id) {
		Role t = null;
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();		
			t = (Role) session.createQuery("from Role where id =:id" )
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
	public void create(Role role) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(role);
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
	public void delete(Role role) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.delete(role);
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
	public void update(Role role) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.update(role);
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
	public Role findByName(String name) {
		Role t = null;
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();		
			t = (Role) session.createQuery("from Role where name =:name" )
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
