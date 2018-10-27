package base.dao;

import java.util.*;

import base.entities.*;

public interface PhotoDAO {
	
	public List<Photo> findAll();
	
	public List<Photo> findByProduct(String name);
	
	public Photo find(int id);
	
	public void create(Photo photo);
	
	public void delete(Photo photo);
	
	public void update(Photo photo);
	
	public List<Photo> search(String keyword);
	
	public Photo findByURL(String url);

}
