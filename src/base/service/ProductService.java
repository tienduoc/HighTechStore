package base.service;

import java.util.*;

import base.entities.*;

public interface ProductService {

	public List<Product> findAll();

	public Product find(Integer id);
	
	public List<Product> search(String keyword);

	public List<Product> latest(int n);

	public List<Product> findAvailable();
	
	public List<Product> findActive();
	
	public List<Product> findByCategory(int id);

	public List<Product> findByParentCategory(int id);

	public List<Product> findRelated(Product product);

	public List<Product> findByProducer(int id);
	
	public Product findByName(String name);

	public void create(Product product);

	public void delete(Product product);

	public void update(Product product);
}
