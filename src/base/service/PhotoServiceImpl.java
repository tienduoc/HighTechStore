package base.service;

import base.dao.*;
import base.entities.*;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

@Service("photoService")
@Transactional
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private PhotoDAO photoDAO;

	@Override
	public Photo find(int id) {
		return photoDAO.find(id);
	}
	
	@Override
	public List<Photo> findAll() {
		return photoDAO.findAll();
	}

	@Override
	public void create(Photo photo) {
		photoDAO.create(photo);
	}

	@Override
	public void delete(Photo photo) {
		photoDAO.delete(photo);
	}

	@Override
	public void update(Photo photo) {
		photoDAO.update(photo);
	}

	@Override
	public List<Photo> search(String keyword) {
		return photoDAO.search(keyword);
	}

	@Override
	public Photo findByURL(String name) {
		return photoDAO.findByURL(name);
	}

	@Override
	public List<Photo> findByProduct(String name) {
		return photoDAO.findByProduct(name);
	}

	
	
	

}
