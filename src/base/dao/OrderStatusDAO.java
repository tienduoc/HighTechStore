package base.dao;

import java.util.List;

import base.entities.*;

public interface OrderStatusDAO {

	public OrderStatus find(Integer id);

	public List<OrderStatus> findAll();

	public OrderStatus findByName(String name);
}
