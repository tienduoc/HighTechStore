package base.service;


import base.dao.*;
import base.entities.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderDetailService")
@Transactional
public class OrderDetailServiceImpl 
	implements OrderDetailService {

	@Autowired
	private OrderDetailDAO orderDetailDAO;
	
	@Override
	public void save(OrderDetail orderDetail) {
		orderDetailDAO.save(orderDetail);
	}
	
	@Override
	public List<OrderDetail> find(int id) {
		return orderDetailDAO.find(id);
	}

}
