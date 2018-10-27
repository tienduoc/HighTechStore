package base.service;

import java.util.*;

import base.entities.*;

public interface ProducerService {

	public List<Producer> findAll();

	public Producer find(Integer id);

	public void create(Producer producer);

	public void delete(Producer producer);

	public void update(Producer producer);
	
	public List<Producer> search(String keyword);
	
	public Producer findByName(String name);

}
