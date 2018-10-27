package base.service;

import base.dao.*;
import base.entities.*;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

@Service("producerService")
@Transactional
public class ProducerServiceImpl implements ProducerService {

	@Autowired
	private ProducerDAO producerDAO;

	@Override
	public Producer find(Integer id) {
		return producerDAO.find(id);
	}
	
	@Override
	public List<Producer> findAll() {
		return producerDAO.findAll();
	}

	@Override
	public void create(Producer producer) {
		producerDAO.create(producer);
	}

	@Override
	public void delete(Producer producer) {
		producerDAO.delete(producer);
	}

	@Override
	public void update(Producer producer) {
		producerDAO.update(producer);
	}

	@Override
	public List<Producer> search(String keyword) {
		return producerDAO.search(keyword);
	}

	@Override
	public Producer findByName(String name) {
		return producerDAO.findByName(name);
	}
	
	

}
