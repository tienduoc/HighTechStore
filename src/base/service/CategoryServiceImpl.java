package base.service;

import base.dao.*;
import base.entities.*;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;

	@Override
	public List<Category> findAll() {
		return categoryDAO.findAll();
	}

	@Override
	public Category find(Integer id) {
		return categoryDAO.find(id);
	}

	@Override
	public List<Category> getParent() {
		return categoryDAO.getParent();
	}

	@Override
	public void create(Category category) {
		categoryDAO.create(category);
		
	}

	@Override
	public void delete(Category category) {
		categoryDAO.delete(category);
		
	}

	@Override
	public void update(Category category) {
		categoryDAO.update(category);
		
	}

	@Override
	public Category findByName(String name) {
		return categoryDAO.findByName(name);
	}

	@Override
	public List<Category> getChild() {
		return categoryDAO.getChild();
	}

	@Override
	public List<Category> search(String keyword) {
		return categoryDAO.search(keyword);
	}

}
