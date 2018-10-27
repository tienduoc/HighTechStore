package base.service;

import java.util.List;

import base.entities.*;

public interface ContactService {

	public List<Contact> findAll();
	
	public Contact find(Integer id);
	
	public void create(Contact contact);
	
	public void delete(Contact contact);
	
	public void update(Contact contact);

	public List<Contact> search(String keyword);
}
