package base.service;


import base.dao.*;
import base.entities.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("contactService")
@Transactional
public class ContactServiceImpl 
	implements ContactService {

	@Autowired
	private ContactDAO contactDAO;

	@Override
	public void create(Contact contact) {
		contactDAO.create(contact);
	}

	@Override
	public List<Contact> findAll() {
		return contactDAO.findAll();
	}

	@Override
	public Contact find(Integer id) {
		return contactDAO.find(id);
	}

	@Override
	public void delete(Contact contact) {
		contactDAO.delete(contact);
	}

	@Override
	public void update(Contact contact) {
		contactDAO.update(contact);
	}

	@Override
	public List<Contact> search(String keyword) {
		return contactDAO.search(keyword);
	}
	

}
