package base.dao;

import java.util.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import base.entities.*;

@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product find(Integer id) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> latest(int n) {
		List<Product> products = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			products = session.createQuery("from Product where quantity >=1 order by id desc").setMaxResults(n).list();
			transaction.commit();
		} catch (Exception e) {
			products = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByCategory(int id) {
		List<Product> products = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			products = session.createQuery("from Product where category.id = :id and quantity >=1 order by id desc")
					.setInteger("id", id)
					.list();
			transaction.commit();
		} catch (Exception e) {
			products = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return products;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByParentCategory(int id) {
		List<Product> products = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			products = session.createQuery("from Product where category.category.id = :id and quantity >=1 order by id desc")
					.setInteger("id", id)
					.list();
			transaction.commit();
		} catch (Exception e) {
			products = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		List<Product> products = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			products = session.createQuery("from Product order by id desc").list();
			transaction.commit();
		} catch (Exception e) {
			products = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return products;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findActive() {
		List<Product> products = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			products = session.createQuery("from Product where status = true ").list();
			transaction.commit();
		} catch (Exception e) {
			products = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return products;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAvailable() {
		List<Product> products = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			products = session.createQuery("from Product where quantity >=1 ").list();
			transaction.commit();
		} catch (Exception e) {
			products = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return products;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findRelated(Product product) {
		List<Product> products = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			products = session.createQuery("from Product where category.id = :categoryId and id != :id ")
					.setInteger("categoryId", product.getCategory().getId())
					.setInteger("id", product.getId()).list();
			transaction.commit();
		} catch (Exception e) {
			products = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return products;
	}

	@Override
	public void create(Product product) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.save(product);
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
	public void delete(Product product) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.delete(product);
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
	public void update(Product product) {
		Transaction transaction = null;
		Session session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			session.update(product);
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
	public List<Product> findByProducer(int id) {
		List<Product> products = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			products = session.createQuery("from Product where producer.id = :id")
					.setInteger("id", id).list();
			transaction.commit();
		} catch (Exception e) {
			products = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return products;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> search(String keyword) {
		List<Product> products = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			products = session.createQuery("select p from Product p where p.name like :keyword")
					.setParameter("keyword", "%" + keyword + "%").list();
			transaction.commit();
		} catch (Exception e) {
			products = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return products;
	}


	@Override
	public Product findByName(String name) {
		Product products = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			products = (Product) session.createQuery("from Product where name = :name")
					.setString("name", name)
					.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			products = null;
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return products;
	}
}
