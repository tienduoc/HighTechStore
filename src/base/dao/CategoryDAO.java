package base.dao;

import java.util.*;

import base.entities.*;

public interface CategoryDAO {

	public List<Category> findAll();
	
	public Category find(Integer id);
	
	public List<Category> getParent();
	
	public List<Category> getChild();
	
	public void create(Category category);
	
	public void delete(Category category);
	
	public void update(Category category);
	
	public Category findByName(String name);
	
	public List<Category> search(String keyword);
}
