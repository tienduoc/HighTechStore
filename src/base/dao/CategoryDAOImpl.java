package base.dao;

import java.util.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import base.entities.*;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAll() {
		List<Category> categories = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			categories = session.createQuery("from Category order by id desc").list();
			transaction.commit();
		} catch (Exception e) {
			categories = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return categories;
	}


	@Override
	public Category find(Integer id) {
		Category t = null;
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();		
			t = (Category) session.createQuery("from Category where id =:id" )
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
	public List<Category> getParent() {
		List<Category> categories = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			categories = session.createQuery("from Category where ParentID= null").list();
			transaction.commit();
		} catch (Exception e) {
			categories = null;
			if(transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return categories;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getChild() {
		List<Category> categories = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			categories = session.createQuery("from Category where ParentID != null").list();
			transaction.commit();
		} catch (Exception e) {
			categories = null;
			if(transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return categories;
	}


	@Override
	public void create(Category category) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(category);
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
	public void delete(Category category) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.delete(category);
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
	public void update(Category category) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.update(category);
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
	public Category findByName(String name) {
		Category t = null;		
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			t = (Category) session.createQuery("from Category where name =:name").setString("name", name)
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> search(String keyword) {
		List<Category> categories = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			categories = session.createQuery("select p from Category p where p.name like :keyword")
					.setParameter("keyword", "%" + keyword + "%").list();
			transaction.commit();
		} catch (Exception e) {
			categories = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return categories;
	}
	
}
