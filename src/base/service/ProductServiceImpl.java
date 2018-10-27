package base.service;

import base.dao.*;
import base.entities.*;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Override
	public Product find(Integer id) {
		return productDAO.find(id);
	}

	@Override
	public List<Product> latest(int n) {
		return productDAO.latest(n);
	}

	@Override
	public List<Product> findByCategory(int id) {
		return productDAO.findByCategory(id);
	}

	@Override
	public List<Product> findAll() {
		return productDAO.findAll();
	}

	@Override
	public List<Product> findByParentCategory(int id) {
		return productDAO.findByParentCategory(id);
	}

	@Override
	public List<Product> findRelated(Product product) {
		return productDAO.findRelated(product);
	}

	@Override
	public void create(Product product) {
		productDAO.create(product);
	}

	@Override
	public void delete(Product product) {
		productDAO.delete(product);
	}

	@Override
	public void update(Product product) {
		productDAO.update(product);
	}
	
	@Override
	public List<Product> findByProducer(int id) {
		return productDAO.findByProducer(id);
	}
	
	@Override
	public List<Product> search(String keyword) {
		return productDAO.search(keyword);
	}

	@Override
	public Product findByName(String name) {
		return productDAO.findByName(name);
	}

	@Override
	public List<Product> findAvailable() {
		return productDAO.findAvailable();
	}

	@Override
	public List<Product> findActive() {
		return productDAO.findActive();
	}
}
