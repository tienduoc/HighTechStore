package base.service;

import base.dao.*;
import base.entities.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderStatusService")
@Transactional
public class OrderStatusServiceImpl 
	implements OrderStatusService {

	@Autowired
	private OrderStatusDAO orderStatusDAO;

	@Override
	public OrderStatus find(Integer id) {
		return orderStatusDAO.find(id);
	}

	@Override
	public List<OrderStatus> findAll() {
		return orderStatusDAO.findAll();
	}

	@Override
	public OrderStatus findByName(String name) {
		return orderStatusDAO.findByName(name);
	}


}
