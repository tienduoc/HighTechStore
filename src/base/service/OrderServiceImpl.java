package base.service;

import base.dao.*;
import base.entities.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderService")
@Transactional
public class OrderServiceImpl 
	implements OrderService {

	@Autowired
	private OrderDAO orderDAO;
	
	@Override
	public void save(Orders orders) {
		orderDAO.save(orders);
	}

	@Override
	public void update(Orders orders) {
		orderDAO.update(orders);
		
	}

	@Override
	public Orders find(int id) {		
		return orderDAO.find(id);
	}

	@Override
	public List<Orders> findByUserName(String username) {
		return orderDAO.findByUserName(username);
	}

	@Override
	public List<Orders> findAll() {
		return orderDAO.findAll();
	}

	@Override
	public List<Orders> search(String username) {
		return orderDAO.search(username);
	}

}
