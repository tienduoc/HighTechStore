package base.dao;

import java.util.List;

import base.entities.*;

public interface OrderDetailDAO {

	public void save(OrderDetail orderDetail);
	
	public List<OrderDetail> find(int id);
}
