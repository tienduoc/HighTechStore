package base.service;

import java.util.List;

import base.entities.*;

public interface OrderStatusService {

	public OrderStatus find(Integer id);

	public List<OrderStatus> findAll();

	public OrderStatus findByName(String name);

}
