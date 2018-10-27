package base.dao;

import java.util.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import base.entities.*;

@Repository("photoDAO")
public class PhotoDAOImpl implements PhotoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> findAll() {
		List<Photo> photos = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			photos = session.createQuery("from Photo order by id desc").list();
			transaction.commit();
		} catch (Exception e) {
			photos = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return photos;
	}

	@Override
	public void create(Photo photos) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(photos);
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
	public void delete(Photo photo) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.delete(photo);
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
	public void update(Photo photo) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.update(photo);
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
	public List<Photo> search(String keyword) {
		List<Photo> photoes = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			photoes = session.createQuery("select p from Photo p where product.name like :keyword")
					.setParameter("keyword", "%" + keyword + "%").list();
			transaction.commit();
		} catch (Exception e) {
			photoes = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return photoes;
	}

	@Override
	public Photo find(int id) {
		Photo t = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			t = (Photo) session.createQuery("from Photo where id =:id").setInteger("id", id).uniqueResult();
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

	@Override
	public List<Photo> findByProduct(String name) {
		List<Photo> photos = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.createQuery("from Photo where product.name = :name").setString("name", name).list();
			transaction.commit();
		} catch (Exception e) {
			photos = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return photos;
	}

	@Override
	public Photo findByURL(String url) {
		Photo photoes = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			photoes = (Photo) session.createQuery("from Photo where product.url = :url")
					.setString("url", url)
					.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			photoes = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return photoes;
	}
}
