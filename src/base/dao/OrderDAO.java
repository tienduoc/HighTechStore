package base.dao;

import java.util.List;

import base.entities.*;

public interface OrderDAO {

	public void save(Orders orders);
	
	public void update(Orders orders);
	
	public Orders find(int id);
	
	public List<Orders> findByUserName(String username);
	
	public List<Orders> findAll();
	
	public List<Orders> search(String username);
}
