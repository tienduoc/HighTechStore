package base.service;

import java.util.List;

import base.entities.*;

public interface OrderDetailService {

	public void save(OrderDetail orderDetail);
	
	public List<OrderDetail> find(int id);
}
